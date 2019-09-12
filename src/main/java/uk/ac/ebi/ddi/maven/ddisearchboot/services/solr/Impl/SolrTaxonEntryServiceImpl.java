package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.Impl;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.IQModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.SolrQueryBuilder;
import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.ISolrTaxonEntryService;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Entry;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.QueryResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SolrTaxonEntryServiceImpl implements ISolrTaxonEntryService {

    @Autowired
    SolrClient solrClient;


    @Override
    public QueryResult getTaxonomy(String domain, IQModel iqModel) {
        SolrQuery solrQuery = SolrQueryBuilder.buildSolrQuery(iqModel);
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(domain, solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        QueryResult queryResult = new QueryResult();
        queryResult.setHitCount(0);
        queryResult.setFacets(null);
        queryResult.setDomains(null);
        Entry[] entries = queryResponse.getResults().stream().map(x -> {
            Entry entry = new Entry();
            entry.setId(String.valueOf(x.get("tax_id")));
            entry.setSource("taxonomy");
            entry.setScore(null);
            Map<String, String[]> map = new LinkedHashMap<>();
            map.put("name", new String[]{String.valueOf(x.get("name_txt"))});
            entry.setFields(map);
            return entry;
        }).toArray(Entry[]::new);
        queryResult.setEntries(entries);
        return queryResult;
    }
}
