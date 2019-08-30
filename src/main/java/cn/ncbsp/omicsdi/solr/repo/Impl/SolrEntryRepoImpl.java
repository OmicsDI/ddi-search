package cn.ncbsp.omicsdi.solr.repo.Impl;

import cn.ncbsp.omicsdi.solr.repo.SolrEntryRepo;
import cn.ncbsp.omicsdi.solr.solrTool.SolrSuggestTemplate;
import cn.ncbsp.omicsdi.solr.solrmodel.SolrEntry;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * @author Xpon
 */
@Repository
public class SolrEntryRepoImpl implements SolrEntryRepo {

    @Autowired
    SolrClient solrClient;

    @Override
    public void saveEntry(String core, SolrInputDocument solrInputDocument) {
        UpdateResponse updateResponse;
        try {
            updateResponse = solrClient.add(core, solrInputDocument);
            assert updateResponse != null;
            if (updateResponse.getStatus() == 0) {
                solrClient.commit(core);
            }
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveEntryList(String core, List<SolrInputDocument> solrInputDocumentList) {
        UpdateResponse updateResponse;
        try {
            updateResponse = solrClient.add(core, solrInputDocumentList);
            assert updateResponse != null;
            if (updateResponse.getStatus() == 0) {
                solrClient.commit(core);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }
}
