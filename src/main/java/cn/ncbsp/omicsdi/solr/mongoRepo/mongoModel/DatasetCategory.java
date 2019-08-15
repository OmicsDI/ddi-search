package cn.ncbsp.omicsdi.solr.mongoRepo.mongoModel;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 04/05/2016
 */
public enum DatasetCategory {

    INSERTED("Inserted"),
    ENRICHED("Enriched"),
    DELETED("Deleted"),
    UPDATED("Updated");

    private final String type;

    DatasetCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
