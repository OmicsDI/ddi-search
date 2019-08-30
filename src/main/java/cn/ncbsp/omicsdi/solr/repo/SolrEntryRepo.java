package cn.ncbsp.omicsdi.solr.repo;

import cn.ncbsp.omicsdi.solr.solrmodel.SolrEntry;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;

import java.util.List;

/**
 * @author Xpon
 */
public interface SolrEntryRepo {
    void saveEntry(String core, SolrInputDocument solrInputDocument);

    void saveEntryList(String core, List<SolrInputDocument> solrInputDocumentList);
//
//    SolrResultPage<T> getQueryResult(String core, Query query, Class<T> clazz);
//
//    FacetPage<T> getFacetQueryResult(String core, FacetQuery query, Class<T> clazz);
//
//    SolrResultPage<T> getSuggestQueryResult(String core, Query query, Class<T> clazz);
}


