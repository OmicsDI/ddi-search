package cn.ncbsp.omicsdi.solr.solrmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 12/05/2015
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class SimilarResult {

    @JsonProperty("entries")
    Entry[] entries;

    public Entry[] getEntries() {
        return entries;
    }

    public void setEntries(Entry[] entries) {
        this.entries = entries;
    }
}
