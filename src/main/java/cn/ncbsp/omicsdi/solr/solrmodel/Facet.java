package cn.ncbsp.omicsdi.solr.solrmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ypriverol
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Facet {

    @JsonProperty("id")
    String id = null;

    @JsonProperty("label")
    String label = null;

    @JsonProperty("total")
    Integer total = null;

    @JsonProperty("facetValues")
    FacetValue[] facetValues = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FacetValue[] getFacetValues() {
        return facetValues;
    }

    public void setFacetValues(FacetValue[] facetValues) {
        this.facetValues = facetValues;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
