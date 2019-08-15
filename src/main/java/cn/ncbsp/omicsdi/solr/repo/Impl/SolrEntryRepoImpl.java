package cn.ncbsp.omicsdi.solr.repo.Impl;

import cn.ncbsp.omicsdi.solr.repo.SolrEntryRepo;
import cn.ncbsp.omicsdi.solr.solrTool.SolrSuggestTemplate;
import cn.ncbsp.omicsdi.solr.solrmodel.SolrEntry;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SolrEntryRepoImpl<T> implements SolrEntryRepo<T> {


    /**
     * 所有的SolrTemplate 都是通过SolrTemplate的构造方法生成
     * 在配置文件中通过构造器方式生成javabean
     */

    @Autowired
    SolrTemplate solrTemplate;

    @Autowired
    SolrSuggestTemplate solrSuggestTemplate;

    @Override
    public void saveEntry(String core, SolrEntry entry) {
        UpdateResponse updateResponse = solrTemplate.saveBean(core, entry);
        if (updateResponse.getStatus() != 0) {
            // todo
            // exception
            /*
            文档上的意思是，回滚不一定能滚全部，一部分autocommit的数据不会回滚，需要关闭autocommit
             */
//            solrTemplate.rollback(core);
        } else {
            solrTemplate.commit(core);
        }

    }

    @Override
    public void saveEntryList(String core, List<SolrEntry> entries) {
        UpdateResponse updateResponse = solrTemplate.saveBeans(core, entries);
        if (updateResponse.getStatus() != 0) {
            // todo
            // exception
//            solrTemplate.rollback(core);
        } else {
            solrTemplate.commit(core);
        }
    }

    @Override
    public SolrResultPage<T> getQueryResult(String core, Query query, Class<T> clazz) {
        SolrResultPage<T> solrEntrySolrResultPage = solrTemplate.query(core, query, clazz);
        return solrEntrySolrResultPage;
    }

    @Override
    public FacetPage<T> getFacetQueryResult(String core, FacetQuery query, Class<T> clazz) {
        FacetPage<T> solrEntrySolrResultPage = solrTemplate.queryForFacetPage(core, query, clazz);
        return solrEntrySolrResultPage;
    }

    @Override
    public SolrResultPage<T> getSuggestQueryResult(String core, Query query, Class<T> clazz) {
        SolrResultPage<T> solrResultPage = solrSuggestTemplate.query(core, query, clazz);

        return solrResultPage;
    }


}
