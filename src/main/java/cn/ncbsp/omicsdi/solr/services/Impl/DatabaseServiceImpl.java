package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.services.IDatabaseService;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Xpon
 */
@Service
public class DatabaseServiceImpl implements IDatabaseService {

    @Autowired
    SolrTemplate solrTemplate;

    @Override
    public String indexSolrData(String collection, SolrInputDocument solrInputDocument) {
        solrTemplate.saveDocument(collection, solrInputDocument);
        solrTemplate.commit(collection);
        return "ok";
    }

    @Override
    public void clearSolrTemplate() {
    }

//    @Override
//    public void deleteDocument(String collection,String param) {
//        SimpleQuery simpleQuery = new SimpleQuery("name:Pride");
//        solrTemplate.delete(collection,simpleQuery);
//        solrTemplate.commit(collection);
//    }
}
