package cn.ncbsp.omicsdi.solr.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;

public class XmlHelper {
    public static <T> T xmlToObject(String xml, T t) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new File(xml));
            return t;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> StringWriter objectToXml(T t, String path, String databaseName) {
        JAXBContext context = null;
        Marshaller ms = null;
        StringWriter sw = new StringWriter();
        try {
            context = JAXBContext.newInstance(t.getClass());
            ms = context.createMarshaller();
            ms.marshal(t, sw);
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ms.marshal(t, new FileOutputStream(path + "\\" + databaseName + System.currentTimeMillis() + (int) (Math.random() * 1000) + ".xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
        return sw;
    }
}
