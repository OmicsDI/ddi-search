package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.queryModel.FacetQueryModel;
import cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder;
import cn.ncbsp.omicsdi.solr.services.ISolrFacetService;
import cn.ncbsp.omicsdi.solr.solrmodel.Facet;
import cn.ncbsp.omicsdi.solr.solrmodel.FacetList;
import cn.ncbsp.omicsdi.solr.solrmodel.FacetValue;
import cn.ncbsp.omicsdi.solr.solrmodel.NCBITaxonomy;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Xpon
 */
@Service
public class SolrFacetServiceImpl implements ISolrFacetService {


    @Autowired
    SolrClient solrClient;


    @Autowired
    SolrEntryServiceImpl solrEntryService;


    @Override
    public FacetList getFacetEntriesByDomains(String core, FacetQueryModel facetQueryModel) {
        if (facetQueryModel == null) {
            // todo exception
        }
        assert facetQueryModel != null;
        SolrQuery solrQuery = SolrQueryBuilder.buildSolrQuery(facetQueryModel);

        QueryResponse queryResponse = null;
        long foundNum = 0L;
        List<FacetField> facetFieldList = null;
        try {
            queryResponse = solrClient.query(core, solrQuery);
            foundNum = queryResponse.getResults().getNumFound();
            facetFieldList = queryResponse.getFacetFields();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FacetList facetList = new FacetList();
////        Facet[] facets = new Facet[facetFieldList.size()];
////        for(int i = 0;i<facetFieldList.size();i++) {
////            String facetName = solrQuery.getFacetFields()[i];
////            Facet facet = new Facet();
////            facet.setId(facetName);
////            facet.setLabel(facetName);
////            Long l = facetFieldList.stream().mapToLong(x -> x.getValues().stream().mapToLong( y -> y.getCount()).sum()).sum();
////        }

        FacetList facetList = new FacetList();
        facetList.setHitCount(String.valueOf(foundNum));


        assert facetFieldList != null;
        Facet[] facets = facetFieldList.stream().map(x -> {
            Facet facet = new Facet();
            facet.setId(x.getName());
            facet.setLabel(labelConverter(x.getName()));
            FacetValue[] facetValues = x.getValues().stream().map(count -> {
                FacetValue facetValue = new FacetValue();

                facetValue.setLabel(count.getName());
                facetValue.setValue(count.getName());
                facetValue.setCount(String.valueOf(count.getCount()));
                return facetValue;
            }).toArray(FacetValue[]::new);

            if ("TAXONOMY".equalsIgnoreCase(facetQueryModel.getFacet_field())) {
                List<NCBITaxonomy> ncbiTaxonomyList = solrEntryService.getNCBITaxonomyData(Arrays.stream(facetValues).map(FacetValue::getLabel).toArray(String[]::new));
                Map<String, String> map = new ConcurrentHashMap<>();
                ncbiTaxonomyList.forEach(z -> map.put(z.getTaxId(), z.getNameTxt()));
                for (FacetValue facetValue : facetValues) {
                    facetValue.setLabel(map.get(facetValue.getLabel()));
                }
            }
            facet.setFacetValues(facetValues);
            facet.setTotal(Math.toIntExact(x.getValues().stream().mapToLong(FacetField.Count::getCount).sum()));
            return facet;
        }).toArray(Facet[]::new);
        facetList.setFacets(facets);
        return facetList;
    }

    private String labelConverter(String label) {
        switch (label) {
            case "TAXONOMY":
                return "Organisms";
            case "tissue":
                return "Tissue";
            case "omics_type":
                return "Omics type";
            case "disease":
                return "Disease";
            default:
                return label;
        }
    }
}
