package cn.ncbsp.omicsdi.solr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 26/06/2015
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class Suggestion {

    @JsonProperty("suggestion")
    String suggestion;

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
