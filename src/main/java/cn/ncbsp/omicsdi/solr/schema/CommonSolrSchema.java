package cn.ncbsp.omicsdi.solr.schema;

public class CommonSolrSchema {
    private String name;
    private CommonSchemaTypeEnum type;
    private String defaultValue;
    private Boolean stored;
    private Boolean indexed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommonSchemaTypeEnum getType() {
        return type;
    }

    public void setType(CommonSchemaTypeEnum type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getStored() {
        return stored;
    }

    public void setStored(Boolean stored) {
        this.stored = stored;
    }

    public Boolean getIndexed() {
        return indexed;
    }

    public void setIndexed(Boolean indexed) {
        this.indexed = indexed;
    }
}
