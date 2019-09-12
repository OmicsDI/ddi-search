package uk.ac.ebi.ddi.maven.ddisearchboot.model.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;
import java.util.Map;

@SolrDocument
public class SolrEntry{
    @Id
    @Field
    String id;

    @Field(value = "repository")
    List<String> repository;

    @Field(value = "omics_type")
    List<String> omicsType;

    @Field(value = "full_dataset_link")
    List<String> fullDatasetLink;

    @Field(value = "submitter")
    List<String> submitter;

    @Field(value = "submitter_email")
    List<String> submitterEmail;

    @Field(value = "submitter_mail")
    List<String> submitterMail;

    @Field(value = "submitter_affiliation")
    List<String> submitterAffiliation;

    @Field(value = "instrument_platform")
    List<String> instrumentPlatform;

    @Field(value = "data_protocol")
    String dataProtocol;

    @Field(value = "sample_protocol")
    String sampleProtocol;

    @Field(value = "name_synonyms")
    String nameSynonyms;

    @Field(value = "description_synonyms")
    String descriptionSynonyms;

    @Field(value = "sample_synonyms")
    String sampleSynonyms;

    @Field(value = "data_synonyms")
    String dataSynonyms;

    @Field(value = "pubmed_abstract")
    String pubmedAbstract;

    @Field(value = "pubmed_title")
    String pubmedTitle;

    @Field(value = "pubmed_authors")
    List<String> pubmedAuthors;

    @Field(value = "pubmed_abstract_synonyms")
    String pubmedAbstractSynonyms;

    @Field(value = "pubmed_title_synonyms")
    String pubmedTitleSynonyms;

    @Field(value = "model")
    List<String> model;

    @Field(value = "submission")
    List<String> submission;

    @Field(value = "software")
    List<String> software;

    @Field(value = "species")
    List<String> species;

    @Field(value = "cell_type")
    List<String> cellType;

    @Field(value = "disease")
    List<String> disease;

    @Field(value = "tissue")
    List<String> tissue;

    @Field(value = "additional_accession")
    List<String> additionalAccession;

    @Field(value = "secondary_accession")
    List<String> secondaryAccession;

    @Field(value = "submitter_keywords")
    List<String> submitterKeywords;

    @Field(value = "dataset_file")
    List<String> datasetFile;

    @Field(value = "file_size")
    String fileSize;

    @Field(value = "file_count")
    String fileCount;

    @Field(value = "ptm_modification")
    List<String> ptmModification;

    @Field(value = "study_factor")
    List<String> studyFactor;

    @Field(value = "technology_type")
    List<String> technologyType;

    @Field(value = "proteomexchange_type_submission")
    List<String> proteomexchangeTypeSubmission;

    @Field(value = "pubchem_id")
    List<String> pubchemId;

    @Field(value = "metabolite_name")
    List<String> metaboliteName;

    @Field(value = "protein_name")
    List<String> proteinName;

    @Field(value = "funding")
    List<String> funding;

    @Field(value = "curator_keywords")
    List<String> curatorKeywords;

    @Field(value = "dataset_type")
    List<String> datasetType;

    @Field(value = "gene_name")
    List<String> geneName;

    @Field(value = "download_count")
    String downloadCount;

    @Field(value = "download_count_scaled")
    String downloadCountScaled;

    @Field(value = "search_count")
    String searchCount;

    @Field(value = "normalized_connections")
    String normalizedConnections;

    @Field(value = "citation_count")
    String citationCount;

    @Field(value = "citation_count_scaled")
    String citationCountScaled;

    @Field(value = "view_count")
    String viewCount;

    @Field(value = "view_count_scaled")
    String viewCountScaled;

    @Field(value = "reanalysis_count")
    String reanalysisCount;

    @Field(value = "reanalysis_count_scaled")
    String reanalysisCountScaled;


    @Field
    String acc;

    @Field
    String name;

    @Field
    String description;

    @Field
    String database;

    @Field(value = "domain_source")
    String domainSource;

    @Field(value = "date_publication")
    String datePublication;

    @Field(value = "date_submission")
    String dateSubmission;

    @Field(value = "date_updated")
    String dateUpdated;

    @Field(value = "date_creation")
    String dateCreation;

    @Field(value = "date_others")
    String dateOthers;


    @Field(value = "TAXONOMY")
    List<String> taxonomy;

    @Field(value = "publication_date")
    String publicationDate;

    @Field(value = "ENSEMBL")
    List<String> ENSEMBL;

    @Field(value = "UNIPROT")
    List<String> UNIPROT;

    @Field(value = "CHEBI")
    List<String> CHEBI;

    @Field(value = "PUBMED")
    List<String> PUBMED;


    @Field(value = "additional_*")
    Map<String, List<String>> additionalFields;

    public SolrEntry() {
    }

    public String getDataProtocol() {
        return dataProtocol;
    }

    public void setDataProtocol(String dataProtocol) {
        this.dataProtocol = dataProtocol;
    }

    public String getSampleProtocol() {
        return sampleProtocol;
    }

    public void setSampleProtocol(String sampleProtocol) {
        this.sampleProtocol = sampleProtocol;
    }

    public List<String> getPUBMED() {
        return PUBMED;
    }

//    @Field(value = "omics_type")
//    List<String> omicsType;

    public void setPUBMED(List<String> PUBMED) {
        this.PUBMED = PUBMED;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getDateSubmission() {
        return dateSubmission;
    }

    public void setDateSubmission(String dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateOthers() {
        return dateOthers;
    }

    public void setDateOthers(String dateOthers) {
        this.dateOthers = dateOthers;
    }

    public List<String> getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(List<String> taxonomy) {
        this.taxonomy = taxonomy;
    }

    public List<String> getTissue() {
        return tissue;
    }

    public void setTissue(List<String> tissue) {
        this.tissue = tissue;
    }

    public List<String> getOmicsType() {
        return omicsType;
    }

    public void setOmicsType(List<String> omicsType) {
        this.omicsType = omicsType;
    }

    public List<String> getDisease() {
        return disease;
    }

    public void setDisease(List<String> disease) {
        this.disease = disease;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPubmedAbstract() {
        return pubmedAbstract;
    }

    public void setPubmedAbstract(String pubmedAbstract) {
        this.pubmedAbstract = pubmedAbstract;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getCitationCount() {
        return citationCount;
    }

    public void setCitationCount(String citationCount) {
        this.citationCount = citationCount;
    }

    public String getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(String searchCount) {
        this.searchCount = searchCount;
    }

    public String getReanalysisCount() {
        return reanalysisCount;
    }

    public void setReanalysisCount(String reanalysisCount) {
        this.reanalysisCount = reanalysisCount;
    }

    public List<String> getSubmitterKeywords() {
        return submitterKeywords;
    }

    public void setSubmitterKeywords(List<String> submitterKeywords) {
        this.submitterKeywords = submitterKeywords;
    }

    public List<String> getCuratorKeywords() {
        return curatorKeywords;
    }

    public void setCuratorKeywords(List<String> curatorKeywords) {
        this.curatorKeywords = curatorKeywords;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<String> getENSEMBL() {
        return ENSEMBL;
    }

    public void setENSEMBL(List<String> ENSEMBL) {
        this.ENSEMBL = ENSEMBL;
    }

    public List<String> getUNIPROT() {
        return UNIPROT;
    }

    public void setUNIPROT(List<String> UNIPROT) {
        this.UNIPROT = UNIPROT;
    }

    public List<String> getCHEBI() {
        return CHEBI;
    }

    public void setCHEBI(List<String> CHEBI) {
        this.CHEBI = CHEBI;
    }

    public String getDownloadCountScaled() {
        return downloadCountScaled;
    }

    public void setDownloadCountScaled(String downloadCountScaled) {
        this.downloadCountScaled = downloadCountScaled;
    }

    public String getCitationCountScaled() {
        return citationCountScaled;
    }

    public void setCitationCountScaled(String citationCountScaled) {
        this.citationCountScaled = citationCountScaled;
    }

    public String getReanalysisCountScaled() {
        return reanalysisCountScaled;
    }

    public void setReanalysisCountScaled(String reanalysisCountScaled) {
        this.reanalysisCountScaled = reanalysisCountScaled;
    }

    public String getViewCountScaled() {
        return viewCountScaled;
    }

    public void setViewCountScaled(String viewCountScaled) {
        this.viewCountScaled = viewCountScaled;
    }

    public List<String> getDatasetFile() {
        return datasetFile;
    }

    public void setDatasetFile(List<String> datasetFile) {
        this.datasetFile = datasetFile;
    }

    public List<String> getSoftware() {
        return software;
    }

    public void setSoftware(List<String> software) {
        this.software = software;
    }

    public List<String> getFullDatasetLink() {
        return fullDatasetLink;
    }

    public void setFullDatasetLink(List<String> fullDatasetLink) {
        this.fullDatasetLink = fullDatasetLink;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }


    public List<String> getRepository() {
        return repository;
    }

    public void setRepository(List<String> repository) {
        this.repository = repository;
    }

    public List<String> getSubmitterEmail() {
        return submitterEmail;
    }

    public void setSubmitterEmail(List<String> submitterEmail) {
        this.submitterEmail = submitterEmail;
    }

    public List<String> getSubmitter() {
        return submitter;
    }

    public void setSubmitter(List<String> submitter) {
        this.submitter = submitter;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getSecondaryAccession() {
        return secondaryAccession;
    }

    public void setSecondaryAccession(List<String> secondaryAccession) {
        this.secondaryAccession = secondaryAccession;
    }

    public String getPubmedTitle() {
        return pubmedTitle;
    }

    public void setPubmedTitle(String pubmedTitle) {
        this.pubmedTitle = pubmedTitle;
    }

    public List<String> getPubmedAuthors() {
        return pubmedAuthors;
    }

    public void setPubmedAuthors(List<String> pubmedAuthors) {
        this.pubmedAuthors = pubmedAuthors;
    }

    public String getSampleSynonyms() {
        return sampleSynonyms;
    }

    public void setSampleSynonyms(String sampleSynonyms) {
        this.sampleSynonyms = sampleSynonyms;
    }

    public String getDataSynonyms() {
        return dataSynonyms;
    }

    public void setDataSynonyms(String dataSynonyms) {
        this.dataSynonyms = dataSynonyms;
    }

    public String getNameSynonyms() {
        return nameSynonyms;
    }

    public void setNameSynonyms(String nameSynonyms) {
        this.nameSynonyms = nameSynonyms;
    }

    public String getDescriptionSynonyms() {
        return descriptionSynonyms;
    }

    public void setDescriptionSynonyms(String descriptionSynonyms) {
        this.descriptionSynonyms = descriptionSynonyms;
    }

    public String getPubmedTitleSynonyms() {
        return pubmedTitleSynonyms;
    }

    public void setPubmedTitleSynonyms(String pubmedTitleSynonyms) {
        this.pubmedTitleSynonyms = pubmedTitleSynonyms;
    }

    public String getPubmedAbstractSynonyms() {
        return pubmedAbstractSynonyms;
    }

    public void setPubmedAbstractSynonyms(String pubmedAbstractSynonyms) {
        this.pubmedAbstractSynonyms = pubmedAbstractSynonyms;
    }

    public String getDomainSource() {
        return domainSource;
    }

    public void setDomainSource(String domainSource) {
        this.domainSource = domainSource;
    }

    public List<String> getSubmitterMail() {
        return submitterMail;
    }

    public void setSubmitterMail(List<String> submitterMail) {
        this.submitterMail = submitterMail;
    }

    public List<String> getSubmitterAffiliation() {
        return submitterAffiliation;
    }

    public void setSubmitterAffiliation(List<String> submitterAffiliation) {
        this.submitterAffiliation = submitterAffiliation;
    }

    public List<String> getInstrumentPlatform() {
        return instrumentPlatform;
    }

    public void setInstrumentPlatform(List<String> instrumentPlatform) {
        this.instrumentPlatform = instrumentPlatform;
    }

    public List<String> getModel() {
        return model;
    }

    public void setModel(List<String> model) {
        this.model = model;
    }

    public List<String> getSubmission() {
        return submission;
    }

    public void setSubmission(List<String> submission) {
        this.submission = submission;
    }

    public List<String> getCellType() {
        return cellType;
    }

    public void setCellType(List<String> cellType) {
        this.cellType = cellType;
    }

    public List<String> getAdditionalAccession() {
        return additionalAccession;
    }

    public void setAdditionalAccession(List<String> additionalAccession) {
        this.additionalAccession = additionalAccession;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileCount() {
        return fileCount;
    }

    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    public List<String> getPtmModification() {
        return ptmModification;
    }

    public void setPtmModification(List<String> ptmModification) {
        this.ptmModification = ptmModification;
    }

    public List<String> getStudyFactor() {
        return studyFactor;
    }

    public void setStudyFactor(List<String> studyFactor) {
        this.studyFactor = studyFactor;
    }

    public List<String> getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(List<String> technologyType) {
        this.technologyType = technologyType;
    }

    public List<String> getProteomexchangeTypeSubmission() {
        return proteomexchangeTypeSubmission;
    }

    public void setProteomexchangeTypeSubmission(List<String> proteomexchangeTypeSubmission) {
        this.proteomexchangeTypeSubmission = proteomexchangeTypeSubmission;
    }

    public List<String> getPubchemId() {
        return pubchemId;
    }

    public void setPubchemId(List<String> pubchemId) {
        this.pubchemId = pubchemId;
    }

    public List<String> getMetaboliteName() {
        return metaboliteName;
    }

    public void setMetaboliteName(List<String> metaboliteName) {
        this.metaboliteName = metaboliteName;
    }

    public List<String> getProteinName() {
        return proteinName;
    }

    public void setProteinName(List<String> proteinName) {
        this.proteinName = proteinName;
    }

    public List<String> getFunding() {
        return funding;
    }

    public void setFunding(List<String> funding) {
        this.funding = funding;
    }

    public List<String> getDatasetType() {
        return datasetType;
    }

    public void setDatasetType(List<String> datasetType) {
        this.datasetType = datasetType;
    }

    public List<String> getGeneName() {
        return geneName;
    }

    public void setGeneName(List<String> geneName) {
        this.geneName = geneName;
    }

    public String getNormalizedConnections() {
        return normalizedConnections;
    }

    public void setNormalizedConnections(String normalizedConnections) {
        this.normalizedConnections = normalizedConnections;
    }
}
