package uk.ac.ebi.ddi.maven.ddisearchboot.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SolrClientConf {

    @Value("${ddi.search.baseUrl}")
    private String baseUrl;

    @Bean(name = "solrClient")
    public SolrClient createSolrClient() {
        return new HttpSolrClient.Builder().withBaseSolrUrl(baseUrl).build();
    }
}
