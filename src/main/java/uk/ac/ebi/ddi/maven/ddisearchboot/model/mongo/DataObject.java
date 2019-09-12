package uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 18/08/2015
 */
public abstract class DataObject implements IDataObject {

    @XmlTransient
    protected long hid;

    public long getHid() {
        return hid;
    }
}
