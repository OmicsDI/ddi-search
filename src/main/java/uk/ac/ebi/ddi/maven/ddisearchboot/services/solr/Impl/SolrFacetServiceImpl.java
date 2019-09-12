package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.Impl;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.FacetQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.SolrQueryBuilder;
import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.IEBISearchTaxonomyService;
import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.ISolrFacetService;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Facet;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.FacetList;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.FacetValue;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.NCBITaxonomy;
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

    @Autowired
    IEBISearchTaxonomyService iebiSearchTaxonomyService;


    @Override
    public FacetList getFacetEntriesByDomains(String core, FacetQueryModel facetQueryModel, String order, String sortField) {
        if (facetQueryModel == null) {
            // todo exception
        }
        assert facetQueryModel != null;
        SolrQuery solrQuery = SolrQueryBuilder.buildSolrQuery(facetQueryModel);

        SolrQueryBuilder.addSort(order, sortField, solrQuery);

        QueryResponse queryResponse = null;
        long foundNum = 0L;
        List<FacetField> facetFieldList = null;
        try {
            queryResponse = solrClient.query(core, solrQuery);
            foundNum = queryResponse.getResults().getNumFound();
            facetFieldList = queryResponse.getFacetFields();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

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

                List<NCBITaxonomy> ncbiTaxonomyList = iebiSearchTaxonomyService.getNCBITaxonomyData(Arrays.stream(facetValues).map(FacetValue::getLabel).toArray(String[]::new));
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
