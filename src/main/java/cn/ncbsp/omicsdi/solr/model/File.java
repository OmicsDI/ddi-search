package cn.ncbsp.omicsdi.solr.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * <p>Java class for dateType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="dateType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileType")
public class File
        implements Serializable, IDataObject {

    private final static long serialVersionUID = 105L;
    @XmlValue
    protected String value;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Default constructor
     */
    public File() {
    }

    /**
     * Constructor with both parameters
     *
     * @param name  type of the date
     * @param value value of the date
     */
    public File(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Date{" +
                "type='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
