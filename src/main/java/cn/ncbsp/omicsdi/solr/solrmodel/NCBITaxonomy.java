package cn.ncbsp.omicsdi.solr.solrmodel;


import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "taxonomy")
public class NCBITaxonomy {
    @Id
    @Field
    String id;

    @Field(value = "tax_id")
    String taxId;

    @Field(value = "name_txt")
    String nameTxt;

    @Field(value = "unique_name")
    String uniqueName;

    @Field(value = "name_class")
    String nameClass;

    public NCBITaxonomy() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(String nameTxt) {
        this.nameTxt = nameTxt;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
}
