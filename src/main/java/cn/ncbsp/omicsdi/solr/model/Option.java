package cn.ncbsp.omicsdi.solr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ypriverol
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class Option {

    @JsonProperty("name")
    String name = null;

    @JsonProperty("value")
    String value = null;

}
