package cn.ncbsp.omicsdi.solr.repo.Impl;

import cn.ncbsp.omicsdi.solr.repo.SolrSchemaRepo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.GenericSolrRequest;
import org.apache.solr.client.solrj.request.RequestWriter;
import org.apache.solr.client.solrj.response.DelegationTokenResponse;
import org.apache.solr.client.solrj.response.SimpleSolrResponse;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Xpon
 * only for english
 */
@Repository
public class SolrSchemaRepoImpl implements SolrSchemaRepo {

    @Autowired
    SolrClient solrClient;

    @Value("${ddi.search.user}")
    private String solrUserName;

    @Value("${ddi.search.password}")
    private String solrPassword;


    @Override
    public List<String> getAllExcludeWords(String collectionName) {
        GenericSolrRequest genericSolrRequest = new GenericSolrRequest(SolrRequest.METHOD.GET, "/schema/analysis/stopwords/english", null);
        genericSolrRequest.setBasicAuthCredentials(solrUserName, solrPassword);
        genericSolrRequest.setResponseParser(new DelegationTokenResponse.JsonMapResponseParser());
        ArrayList<String> currentStopWords = null;
        try {
            SimpleSolrResponse simpleSolrResponse = genericSolrRequest.process(solrClient, collectionName);
            NamedList<Object> nl = simpleSolrResponse.getResponse();
            LinkedHashMap<String, ArrayList<String>> wordSet = (LinkedHashMap<String, ArrayList<String>>) nl.get("wordSet");
            currentStopWords = wordSet.get("managedList");
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return currentStopWords;
    }

    @Override
    public Integer saveExcludeWords(List<String> strings, String collectionName) {
        if(null == strings || strings.size() < 1) {
            return 0;
        }

        GenericSolrRequest genericSolrRequest = new GenericSolrRequest(SolrRequest.METHOD.PUT, "/schema/analysis/stopwords/english", null);
        genericSolrRequest.setBasicAuthCredentials(solrUserName, solrPassword);
        genericSolrRequest.setResponseParser(new DelegationTokenResponse.JsonMapResponseParser());
        genericSolrRequest.setContentWriter(new RequestWriter.StringPayloadContentWriter(JSONArray.toJSONString(strings), "application/json;charset=utf-8"));
        String status = null;
        try {
            SimpleSolrResponse simpleSolrResponse = genericSolrRequest.process(solrClient, collectionName);
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) simpleSolrResponse.getResponse().get("responseHeader");
            Object o = map.get("status");
            status = String.valueOf(o);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert status != null;
        if("0".equals(status)) {
            return strings.size();
        } else {
            return 0;
        }
    }
}
