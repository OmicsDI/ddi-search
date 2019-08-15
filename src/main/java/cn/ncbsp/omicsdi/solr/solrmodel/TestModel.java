package cn.ncbsp.omicsdi.solr.solrmodel;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SolrDocument(collection = "omicsdi")
public class TestModel {

    @Field(value = "*")
    HashMap<String, List<String>> map;

    public TestModel(HashMap<String, List<String>> map) {
        this.map = map;
    }

    public Map<String, List<String>> getMap() {
        return map;
    }

    public void setMap(HashMap<String, List<String>> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "map=" + map +
                '}';
    }
}
