package cn.ncbsp.omicsdi.solr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ypriverol
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldInfo {

    @JsonProperty("id")
    String id = null;

    @JsonProperty("name")
    String name = null;

    @JsonProperty("description")
    String description;

    @JsonProperty("options")
    Option[] options = null;
}
