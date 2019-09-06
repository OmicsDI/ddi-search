package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.services.IEBISearchTaxonomyService;
import cn.ncbsp.omicsdi.solr.solrmodel.NCBITaxonomy;
import cn.ncbsp.omicsdi.solr.solrmodel.QueryResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpClientConnection;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EBISearchTaxonomyServiceImpl implements IEBISearchTaxonomyService {

    @Value("${ebi.search.taxonomy}")
    private String url;

    @Override
    public List<NCBITaxonomy> getNCBITaxonomyData(String ...id) {

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme("https").host(url).path("/ebisearch/ws/rest/taxonomy/entry").path("/"+ StringUtils.join(id, ",")).queryParam("fields", new Object[]{"name"}).queryParam("format", new Object[]{"JSON"});

        RestTemplate restTemplate = new RestTemplate();

        QueryResult queryResult = restTemplate.getForObject(builder.toUriString(), QueryResult.class);
        return Arrays.stream(queryResult.getEntries()).map(x -> {
            NCBITaxonomy ncbiTaxonomy = new NCBITaxonomy();
            ncbiTaxonomy.setId(x.getId());
            ncbiTaxonomy.setTaxId(x.getId());
            ncbiTaxonomy.setNameTxt(((String[])x.getFields().get("name"))[0]);
            return ncbiTaxonomy;
        }).collect(Collectors.toList());
    }
}
