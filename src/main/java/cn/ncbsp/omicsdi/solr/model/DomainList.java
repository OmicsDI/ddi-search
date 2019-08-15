package cn.ncbsp.omicsdi.solr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ypriverol
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainList {

    @JsonProperty("domains")
    public Domain[] domains;

    public Domain[] getList() {
        return domains;
    }

    public void setList(Domain[] list) {
        this.domains = list;
    }


}
