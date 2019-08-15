package cn.ncbsp.omicsdi.solr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "new_core")
public class Money {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    private String name;
    private String manu;
    private String manu_id_s;
    private String cat;
    private String features;
    private String price_c;
    private boolean inStock;

    public Money() {
    }

    public Money(String id, String name, String manu, String manu_id_s, String cat, String features, String price_c, boolean inStock) {
        this.id = id;
        this.name = name;
        this.manu = manu;
        this.manu_id_s = manu_id_s;
        this.cat = cat;
        this.features = features;
        this.price_c = price_c;
        this.inStock = inStock;
    }

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

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public String getManu_id_s() {
        return manu_id_s;
    }

    public void setManu_id_s(String manu_id_s) {
        this.manu_id_s = manu_id_s;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getPrice_c() {
        return price_c;
    }

    public void setPrice_c(String price_c) {
        this.price_c = price_c;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "Money{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manu='" + manu + '\'' +
                ", manu_id_s='" + manu_id_s + '\'' +
                ", cat='" + cat + '\'' +
                ", features='" + features + '\'' +
                ", price_c='" + price_c + '\'' +
                ", inStock=" + inStock +
                '}';
    }
//    private String[] name;
//    private String[] manu;
//    private String manu_id_s;
//    private String[] cat;
//    private String[] features;
//    private String[] price_c;
//    private boolean[] inStock;
//    private String[] name_str;
//    private String[] features_str;
//    private String[] cat_str;
//    private String[] manu_str;
//    private String[] price_c_str;


}
