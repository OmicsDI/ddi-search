package cn.ncbsp.omicsdi.solr.model;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 26/06/2015
 */
public class Item {

    String name = null;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
