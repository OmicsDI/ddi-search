package cn.ncbsp.omicsdi.solr.solrmodel;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class EBISolrEntry {
    @Id
    @Field
    String id;

    @Field
    String acc;

    @Field
    String acronym;

    @Field
    String category;

    @Field
    int citation_count;

    @Field
    double citation_count_scaled;
    @Field
    String contact_person;
    @Field
    String dataset_title;
    @Field
    String dataset_type;
    @Field
    String description;
    @Field
    String description_synonyms;
    @Field
    String domain_source;
    @Field
    double download_count_scaled;
    @Field
    String email;
    @Field
    String full_dataset_link;
    @Field
    String host;
    @Field
    String host_link;
    @Field
    String name;
    @Field
    String name_synonyms;
    @Field
    double normalized_connections;
    @Field
    String omics_type;
    @Field
    String output_date;
    @Field
    String policy;
    @Field
    String pubmed_abstract;
    @Field
    String pubmed_abstract_synonyms;
    @Field
    String pubmed_authors;
    @Field
    String pubmed_title;
    @Field
    String pubmed_title_synonyms;
    @Field
    int reanalysis_count;
    @Field
    double reanalysis_count_scaled;
    @Field
    String repository;
    @Field
    String sample_count;
    @Field
    int search_count;
    @Field
    String search_domains;
    @Field
    String secondary_accession;
    @Field
    String source;
    @Field
    String study_type;
    @Field
    String technology_type;
    @Field
    String title;
    @Field
    String updated_date;
    @Field
    String url;
    @Field
    int view_count;
    @Field
    double view_count_scaled;
    @Field
    List<String> PUBMED;
    @Field
    List<String> TAXONOMY;

    @Field(value = "date_publication")
    Date datePublication;

    @Field(value = "date_submission")
    Date dateSubmission;

    @Field(value = "date_updated")
    Date dateUpdated;

    @Field(value = "date_creation")
    Date dateCreation;

    @Field(value = "date_others")
    Date dateOthers;


    @Field(value = "additional_*")
    Map<String, List<String>> additionalFields;

//    @Field(value = "omics_type")
//    List<String> omicsType;

    public Map<String, List<String>> getAdditionalFields() {
        return additionalFields;
    }

    public void setAdditionalFields(Map<String, List<String>> additionalFields) {
        this.additionalFields = additionalFields;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCitation_count() {
        return citation_count;
    }

    public void setCitation_count(int citation_count) {
        this.citation_count = citation_count;
    }

    public double getCitation_count_scaled() {
        return citation_count_scaled;
    }

    public void setCitation_count_scaled(double citation_count_scaled) {
        this.citation_count_scaled = citation_count_scaled;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getDataset_title() {
        return dataset_title;
    }

    public void setDataset_title(String dataset_title) {
        this.dataset_title = dataset_title;
    }

    public String getDataset_type() {
        return dataset_type;
    }

    public void setDataset_type(String dataset_type) {
        this.dataset_type = dataset_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_synonyms() {
        return description_synonyms;
    }

    public void setDescription_synonyms(String description_synonyms) {
        this.description_synonyms = description_synonyms;
    }

    public String getDomain_source() {
        return domain_source;
    }

    public void setDomain_source(String domain_source) {
        this.domain_source = domain_source;
    }

    public double getDownload_count_scaled() {
        return download_count_scaled;
    }

    public void setDownload_count_scaled(double download_count_scaled) {
        this.download_count_scaled = download_count_scaled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_dataset_link() {
        return full_dataset_link;
    }

    public void setFull_dataset_link(String full_dataset_link) {
        this.full_dataset_link = full_dataset_link;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost_link() {
        return host_link;
    }

    public void setHost_link(String host_link) {
        this.host_link = host_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_synonyms() {
        return name_synonyms;
    }

    public void setName_synonyms(String name_synonyms) {
        this.name_synonyms = name_synonyms;
    }

    public double getNormalized_connections() {
        return normalized_connections;
    }

    public void setNormalized_connections(double normalized_connections) {
        this.normalized_connections = normalized_connections;
    }

    public String getOmics_type() {
        return omics_type;
    }

    public void setOmics_type(String omics_type) {
        this.omics_type = omics_type;
    }

    public String getOutput_date() {
        return output_date;
    }

    public void setOutput_date(String output_date) {
        this.output_date = output_date;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getPubmed_abstract() {
        return pubmed_abstract;
    }

    public void setPubmed_abstract(String pubmed_abstract) {
        this.pubmed_abstract = pubmed_abstract;
    }

    public String getPubmed_abstract_synonyms() {
        return pubmed_abstract_synonyms;
    }

    public void setPubmed_abstract_synonyms(String pubmed_abstract_synonyms) {
        this.pubmed_abstract_synonyms = pubmed_abstract_synonyms;
    }

    public String getPubmed_authors() {
        return pubmed_authors;
    }

    public void setPubmed_authors(String pubmed_authors) {
        this.pubmed_authors = pubmed_authors;
    }

    public String getPubmed_title() {
        return pubmed_title;
    }

    public void setPubmed_title(String pubmed_title) {
        this.pubmed_title = pubmed_title;
    }

    public String getPubmed_title_synonyms() {
        return pubmed_title_synonyms;
    }

    public void setPubmed_title_synonyms(String pubmed_title_synonyms) {
        this.pubmed_title_synonyms = pubmed_title_synonyms;
    }

    public int getReanalysis_count() {
        return reanalysis_count;
    }

    public void setReanalysis_count(int reanalysis_count) {
        this.reanalysis_count = reanalysis_count;
    }

    public double getReanalysis_count_scaled() {
        return reanalysis_count_scaled;
    }

    public void setReanalysis_count_scaled(double reanalysis_count_scaled) {
        this.reanalysis_count_scaled = reanalysis_count_scaled;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getSample_count() {
        return sample_count;
    }

    public void setSample_count(String sample_count) {
        this.sample_count = sample_count;
    }

    public int getSearch_count() {
        return search_count;
    }

    public void setSearch_count(int search_count) {
        this.search_count = search_count;
    }

    public String getSearch_domains() {
        return search_domains;
    }

    public void setSearch_domains(String search_domains) {
        this.search_domains = search_domains;
    }

    public String getSecondary_accession() {
        return secondary_accession;
    }

    public void setSecondary_accession(String secondary_accession) {
        this.secondary_accession = secondary_accession;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStudy_type() {
        return study_type;
    }

    public void setStudy_type(String study_type) {
        this.study_type = study_type;
    }

    public String getTechnology_type() {
        return technology_type;
    }

    public void setTechnology_type(String technology_type) {
        this.technology_type = technology_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public double getView_count_scaled() {
        return view_count_scaled;
    }

    public void setView_count_scaled(double view_count_scaled) {
        this.view_count_scaled = view_count_scaled;
    }

    public List<String> getPUBMED() {
        return PUBMED;
    }

    public void setPUBMED(List<String> PUBMED) {
        this.PUBMED = PUBMED;
    }

    public List<String> getTAXONOMY() {
        return TAXONOMY;
    }

    public void setTAXONOMY(List<String> TAXONOMY) {
        this.TAXONOMY = TAXONOMY;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Date getDateSubmission() {
        return dateSubmission;
    }

    public void setDateSubmission(Date dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateOthers() {
        return dateOthers;
    }

    public void setDateOthers(Date dateOthers) {
        this.dateOthers = dateOthers;
    }
}
