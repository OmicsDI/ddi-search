package cn.ncbsp.omicsdi.solr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ypriverol
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class IndexInfo {

    @JsonProperty("name")
    String name = null;

    @JsonProperty("value")
    String value = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
