package cn.ncbsp.omicsdi.solr.solrmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol ypriverol
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class FacetValue {

    @JsonProperty("label")
    String label = null;

    @JsonProperty("value")
    String value = null;

    @JsonProperty("count")
    String count = null;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
