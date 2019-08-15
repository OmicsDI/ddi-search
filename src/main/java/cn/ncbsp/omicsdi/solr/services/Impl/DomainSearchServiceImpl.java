package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.queryModel.QueryModel;
import cn.ncbsp.omicsdi.solr.repo.SolrEntryRepo;
import cn.ncbsp.omicsdi.solr.services.IDomainSearchService;
import cn.ncbsp.omicsdi.solr.solrmodel.*;
import cn.ncbsp.omicsdi.solr.util.SolrQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Xpon
 */
@Service
public class DomainSearchServiceImpl implements IDomainSearchService {

    @Autowired
    SolrEntryRepo<SolrEntry> solrEntryRepo;

    @Override
    public QueryResult getQueryResult(QueryModel queryModel) {

        QueryResult queryResult = new QueryResult();

        SimpleFacetQuery simpleFacetQuery = SolrQueryBuilder.buildSimpleFacetQuery(queryModel);

        SolrResultPage<SolrEntry> facetPage = (SolrResultPage<SolrEntry>) solrEntryRepo.getFacetQueryResult(queryModel.getDomain(), simpleFacetQuery, SolrEntry.class);

        System.out.println("ok");

        queryResult.setCount((int) facetPage.getTotalElements());


        List<SolrEntry> solrEntryList = facetPage.getContent();

        List<Entry> entryList = new ArrayList<Entry>();
        for (SolrEntry solrEntry : solrEntryList) {
            Map<String, List<String>> map = solrEntry.getAdditionalFields();
            Entry entry = new Entry();
            entry.setId(solrEntry.getId());
            entry.setSource(solrEntry.getDatabase());

            Map<String, String[]> formatedMap = new HashMap<String, String[]>();
            Set<String> keyset = map.keySet();
            for (String key : keyset) {
                List<String> list = map.get(key);
                String[] value = new String[list.size()];
                value = list.toArray(value);

                String formatedKey = key.substring(11);
                formatedMap.put(formatedKey, value);
            }
            entry.setFields(formatedMap);

            entryList.add(entry);
        }

        Entry[] entryArray = new Entry[entryList.size()];
        entryArray = entryList.toArray(entryArray);

        // setEntry
        queryResult.setEntries(entryArray);


        // 获取facet后的数据
        Collection<Page<FacetFieldEntry>> collection = facetPage.getFacetResultPages();
        // 转化Collection to ArrayList （方便操作？）
        ArrayList<SolrResultPage<SimpleFacetFieldEntry>> list = new ArrayList(collection);

        // 循环遍历生成facet数组对象
        List<Facet> facets = new ArrayList<Facet>();
        for (SolrResultPage<SimpleFacetFieldEntry> srp : list) {
            List<SimpleFacetFieldEntry> listffe = srp.getContent();
            Facet facet = new Facet();
            int total = 0;
            List<FacetValue> facetValues = new ArrayList<FacetValue>();
            for (SimpleFacetFieldEntry sffe : listffe) {
                facet.setId(sffe.getField().getName());
                facet.setLabel(sffe.getField().getName());
                total = total + (int) sffe.getValueCount();
                FacetValue facetValue = new FacetValue();
                facetValue.setLabel(sffe.getValue());
                facetValue.setValue(sffe.getValue());
                facetValue.setCount(Long.toString(sffe.getValueCount()));
                facetValues.add(facetValue);
            }
            facet.setTotal(total);

            FacetValue[] facetValuesArray = new FacetValue[facetValues.size()];
            facetValuesArray = facetValues.toArray(facetValuesArray);
            facet.setFacetValues(facetValuesArray);

            facets.add(facet);
        }

        Facet[] facetArray = new Facet[facets.size()];
        facetArray = facets.toArray(facetArray);

        // set facets
        queryResult.setFacets(facetArray);

        // set domain
        Domains domains = new Domains();
        queryResult.setDomains(null);

        return queryResult;
    }
}
