package cn.ncbsp.omicsdi.solr.solrmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ypriverol
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Term {

    @JsonProperty("text")
    String text;

    @JsonProperty("docFreq")
    String docFreq;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDocFreq() {
        return docFreq;
    }

    public void setDocFreq(String docFreq) {
        this.docFreq = docFreq;
    }

    @Override
    public String toString() {
        return "Term{" +
                "text='" + text + '\'' +
                ", frequency='" + docFreq + '\'' +
                '}';
    }
}
