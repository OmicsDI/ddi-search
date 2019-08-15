package cn.ncbsp.omicsdi.solr.solrmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yasset Perez-Riverol ypriverol
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {

    @JsonProperty("id")
    String id = null;

    @JsonProperty("score")
    String score;

    @JsonProperty("source")
    String source = null;

    @JsonProperty("fields")
    Map<String, String[]> fields = new HashMap<>();

    public Map<String, String[]> getFields() {
        return fields;
    }

    public void setFields(Map<String, String[]> fields) {
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
