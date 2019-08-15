package cn.ncbsp.omicsdi.solr.solrmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gaur on 14/02/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Domains {

    @JsonProperty("id")
    String id;

    @JsonProperty("hitCount")
    Integer hitCount;

    @JsonProperty("subdomains")
    Domains[] subdomains;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    public Domains[] getSubdomains() {
        return subdomains;
    }

    public void setSubdomains(Domains[] subdomains) {
        this.subdomains = subdomains;
    }


}
