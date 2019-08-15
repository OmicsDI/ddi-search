package cn.ncbsp.omicsdi.solr.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 26/06/2015
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Suggestions {

    @JsonProperty("suggestions")
    Suggestion[] suggestions;

    public Suggestion[] getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Suggestion[] suggestions) {
        this.suggestions = suggestions;
    }
}
