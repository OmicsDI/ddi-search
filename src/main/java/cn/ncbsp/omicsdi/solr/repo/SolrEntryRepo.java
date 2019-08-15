package cn.ncbsp.omicsdi.solr.repo;

import cn.ncbsp.omicsdi.solr.solrmodel.SolrEntry;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;

import java.util.List;

public interface SolrEntryRepo<T> {
    void saveEntry(String core, SolrEntry entry);

    void saveEntryList(String core, List<SolrEntry> entries);

    SolrResultPage<T> getQueryResult(String core, Query query, Class<T> clazz);

    FacetPage<T> getFacetQueryResult(String core, FacetQuery query, Class<T> clazz);

    SolrResultPage<T> getSuggestQueryResult(String core, Query query, Class<T> clazz);
}


