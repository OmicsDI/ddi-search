package cn.ncbsp.omicsdi.solr.solrmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * @author @ypriverol Yasset Perez-Riverol
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class TermResult {

    @JsonProperty("topTerms")
    Term[] topTerms;

    @JsonProperty("totalTermCount")
    int totalTermCount;

    public Term[] getTopTerms() {
        return topTerms;
    }

    public void setTopTerms(Term[] topTerms) {
        this.topTerms = topTerms;
    }

    public int getTotalTermCount() {
        return totalTermCount;
    }

    public void setTotalTermCount(int totalTermCount) {
        this.totalTermCount = totalTermCount;
    }

    @Override
    public String toString() {
        return "TermResult{" +
                "terms=" + Arrays.toString(topTerms) +
                ", totalTermCount=" + totalTermCount +
                '}';
    }
}
