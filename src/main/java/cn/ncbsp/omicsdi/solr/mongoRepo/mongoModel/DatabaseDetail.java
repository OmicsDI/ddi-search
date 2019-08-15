package cn.ncbsp.omicsdi.solr.mongoRepo.mongoModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by root on 16.05.17.
 */
@Document(collection = "databases")
public class DatabaseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    String databaseName;
    //TODO:rename

    String title;

    String sourceUrl;

    String imgAlt;

    String repository;

    String source;
    String orcidName;
    String domain;
    String description;
    byte[] image;
    String icon;
    String urlTemplate;
    Set<String> accessionPrefix;

    public String getOrcidName() {
        return orcidName;
    }

    public void setOrcidName(String orcidName) {
        this.orcidName = orcidName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }

    public Set<String> getAccessionPrefix() {
        return accessionPrefix;
    }

    public void setAccessionPrefix(Set<String> accessionPrefix) {
        this.accessionPrefix = accessionPrefix;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String source) {
        this.repository = source;
    }

    public String getImgAlt() {
        return imgAlt;
    }

    public void setImgAlt(String imgAlt) {
        this.imgAlt = imgAlt;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
