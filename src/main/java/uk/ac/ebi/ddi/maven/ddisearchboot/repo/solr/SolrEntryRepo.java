package uk.ac.ebi.ddi.maven.ddisearchboot.repo.solr;

import org.apache.solr.common.SolrInputDocument;

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


