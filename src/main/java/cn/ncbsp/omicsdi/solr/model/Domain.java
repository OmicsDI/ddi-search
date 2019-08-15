package cn.ncbsp.omicsdi.solr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ypriverol
 */


@JsonIgnoreProperties(ignoreUnknown = true)

public class Domain {

    @JsonProperty("id")
    String id = null;

    @JsonProperty("name")
    String name = null;

    @JsonProperty("description")
    String description = null;

    @JsonProperty("indexInfos")
    IndexInfo[] indexInfos = null;

    @JsonProperty("fieldInfos")
    FieldInfo[] fieldInfos;

    @JsonProperty("subdomains")
    Domain[] subdomains = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IndexInfo[] getIndexInfos() {
        return indexInfos;
    }

    public void setIndexInfos(IndexInfo[] indexInfos) {
        this.indexInfos = indexInfos;
    }

    public FieldInfo[] getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(FieldInfo[] fieldInfos) {
        this.fieldInfos = fieldInfos;
    }

    public Domain[] getSubdomains() {
        return subdomains;
    }

    public void setSubdomains(Domain[] subdomains) {
        this.subdomains = subdomains;
    }
}
