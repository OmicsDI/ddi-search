package cn.ncbsp.omicsdi.solr.model;

import cn.ncbsp.omicsdi.solr.util.Field;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for datesType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="datesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="date" type="{}dateType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filesType", propOrder = {
        "file"
})
public class FilesType
        implements Serializable, IDataObject {

    private final static long serialVersionUID = 105L;
    @XmlElement(required = true)
    protected List<File> file;

    public void setFile(List<File> date) {
        this.file = date;
    }

    /**
     * Gets the value of the date property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the date property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDate().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Date }
     */
    public List<File> getFile() {
        if (file == null) {
            file = new ArrayList<>();
        }
        return this.file;
    }

    public boolean isEmpty() {
        file = getFile();
        return file.isEmpty();
    }


}
