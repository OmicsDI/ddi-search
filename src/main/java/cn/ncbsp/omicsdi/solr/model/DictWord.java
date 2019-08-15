package cn.ncbsp.omicsdi.solr.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 26/06/2015
 */
public class DictWord {

    int total_count = 0;

    List<Item> items = new ArrayList<>();

    public DictWord() {

    }

    public DictWord(int total_count, List<Item> items) {
        this.total_count = total_count;
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


}
