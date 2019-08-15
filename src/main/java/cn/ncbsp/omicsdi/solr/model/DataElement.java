package cn.ncbsp.omicsdi.solr.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 18/08/2015
 */
public enum DataElement {

    ENTRY("/database/entries/entry", Entry.class),
    DATABASE("/database/", Database.class);

    private static final Set<String> xpaths;

    static {
        xpaths = new HashSet<>();
        for (DataElement xpath : values()) {
            xpaths.add(xpath.getXpath());
        }
    }

    private final String xpath;
    @SuppressWarnings("rawtypes")
    private final Class type;

    DataElement(String xpath, @SuppressWarnings("rawtypes") Class clazz) {
        this.xpath = xpath;
        this.type = clazz;
    }

    public static Set<String> getXpaths() {
        return xpaths;
    }

    public String getXpath() {
        return xpath;
    }

    @SuppressWarnings("rawtypes")
    public Class getClassType() {
        return type;
    }

}
