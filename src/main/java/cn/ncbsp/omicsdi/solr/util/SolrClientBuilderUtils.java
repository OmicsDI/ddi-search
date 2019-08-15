package cn.ncbsp.omicsdi.solr.util;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrClientBuilderUtils {

    public static HttpSolrClient setHttpSolrClient(String baseSolrUrl) {
        return new HttpSolrClient(new HttpSolrClient.Builder(baseSolrUrl)) {
        };
    }
}
