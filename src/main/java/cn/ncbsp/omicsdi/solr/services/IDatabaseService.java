package cn.ncbsp.omicsdi.solr.services;

import org.apache.solr.common.SolrInputDocument;

/**
 * @author Xpon
 */
public interface IDatabaseService {
    public String indexSolrData(String collection, SolrInputDocument solrInputDocument);

    void clearSolrTemplate();
//    void deleteDocument(String collection,String param);
}
