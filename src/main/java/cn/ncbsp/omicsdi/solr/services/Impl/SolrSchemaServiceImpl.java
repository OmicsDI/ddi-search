package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.model.Entry;
import cn.ncbsp.omicsdi.solr.schema.CommonSchemaTypeEnum;
import cn.ncbsp.omicsdi.solr.schema.CommonSolrSchema;
import cn.ncbsp.omicsdi.solr.services.ISolrSchemaService;
import cn.ncbsp.omicsdi.solr.solrmodel.SolrEntry;
import cn.ncbsp.omicsdi.solr.util.SolrSchemaUtil;
import jdk.nashorn.internal.objects.annotations.Property;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.apache.solr.client.solrj.response.schema.SchemaResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SolrSchemaServiceImpl implements ISolrSchemaService {

    @Value("${ddi.search.user}")
    private String solrUserName;

    @Value("${ddi.search.password}")
    private String solrPassword;


    private final
    SolrClient solrClient;

    @Autowired
    public SolrSchemaServiceImpl(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @Override
    public Integer autoGenerateField(String fieldName, String collection) {
        CommonSolrSchema commonSolrSchema = new CommonSolrSchema();
        commonSolrSchema.setName(fieldName);
        commonSolrSchema.setType(CommonSchemaTypeEnum.STRINGS);
        commonSolrSchema.setStored(true);
        commonSolrSchema.setIndexed(true);
        Map<String, Object> map = SolrSchemaUtil.getSolrSchemaMap(commonSolrSchema);
        SchemaRequest.AddField addField = new SchemaRequest.AddField(map);
        addField.setBasicAuthCredentials(solrUserName, solrPassword);
        SchemaResponse.UpdateResponse schemaResponse = null;
        try {
            schemaResponse = addField.process(solrClient, collection);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert schemaResponse != null;
        if(schemaResponse.getStatus() == 0) {
            try {
                solrClient.commit(collection);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
            }
            return 1;
        } else {
            try {
                solrClient.rollback(collection);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    @Override
    public Boolean checkFieldExists(String fieldName, String collection) {

        SchemaRequest.Fields fields = new SchemaRequest.Fields();
        fields.setBasicAuthCredentials(solrUserName, solrPassword);
        SchemaResponse.FieldsResponse fieldsResponse = null;
        try {
            fieldsResponse = fields.process(solrClient, collection);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert fieldsResponse != null;

        List<Map<String, Object>> currentFields = fieldsResponse.getFields();

        if(currentFields.stream().anyMatch(x -> x.get("name").toString().equalsIgnoreCase(fieldName))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> getAllFields(String collection) {
        SchemaRequest.Fields fields = new SchemaRequest.Fields();
        fields.setBasicAuthCredentials(solrUserName, solrPassword);
        SchemaResponse.FieldsResponse fieldsResponse = null;
        try {
            fieldsResponse = fields.process(solrClient, collection);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert fieldsResponse != null;

        List<Map<String, Object>> currentFields = fieldsResponse.getFields();
        return currentFields.stream().map(x -> x.get("name").toString()).collect(Collectors.toList());
    }

    @Override
    public Integer manuallyGenerateField(CommonSolrSchema commonSolrSchema, String collection) {
        Map<String, Object> map = SolrSchemaUtil.getSolrSchemaMap(commonSolrSchema);
        SchemaRequest.AddField addField = new SchemaRequest.AddField(map);
        addField.setBasicAuthCredentials(solrUserName, solrPassword);
        SchemaResponse.UpdateResponse schemaResponse = null;
        try {
            schemaResponse = addField.process(solrClient, collection);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        assert schemaResponse != null;
        if(schemaResponse.getStatus() == 0) {
            try {
                solrClient.commit(collection);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
            }
            return 1;
        } else {
            try {
                solrClient.rollback(collection);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    @Override
    public List<SolrInputDocument> parseEntryToSolrInputDocument(List<Entry> list, String database, String core) {
        List<SolrInputDocument> solrInputDocumentList = new ArrayList<>();
        List<String> currentFieldName = getAllFields(core);
        list.forEach(entry -> {
            SolrEntry solrEntry = new SolrEntry();
            solrEntry.setId(entry.getId());
            solrEntry.setAcc(entry.getAcc());
            solrEntry.setName(entry.getName().getValue());// ???
            solrEntry.setDescription(entry.getDescription());
            if (entry.getDates() != null) {
                entry.getDates().getDate().forEach(date -> {
                    switch (date.getType()) {
                        case "publication":
                            solrEntry.setDatePublication(date.getValue());
                            if(StringUtils.isNotBlank(date.getValue())) {
                                if(date.getValue().matches("[a-zA-Z]{3}\\s[a-zA-Z]{3}\\s[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}\\s[a-zA-Z]{3}\\s[0-9]{4}")) {
                                    solrEntry.setPublicationDate(date.getValue().substring(date.getValue().length()-4));
                                }else if (date.getValue().matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")) {
                                    solrEntry.setPublicationDate(date.getValue().substring(date.getValue().length()-4));
                                }else {
                                    solrEntry.setPublicationDate(date.getValue().substring(0,4));
                                }
                            }

                        case "submission ":
                            solrEntry.setDateSubmission(date.getValue());
                        case "updated":
                            solrEntry.setDateUpdated(date.getValue());
                        case "creation":
                            solrEntry.setDateCreation(date.getValue());
                        default:
                            solrEntry.setDateOthers(date.getValue());
                    }
                });
            }
            solrEntry.setDatabase(core);
            solrEntry.setDomainSource(database);
            Map<String, List<String>> additionalMap = new HashMap<>();
            List<String> taxonomy = new ArrayList<>();
            List<String> ensembl = new ArrayList<>();
            List<String> uniport = new ArrayList<>();
            List<String> chebi = new ArrayList<>();
            List<String> pubmed = new ArrayList<>();
            entry.getCrossReferences().getRef().forEach(reference -> {
                if (reference.getDbkey().equalsIgnoreCase("TAXONOMY")) {
                    taxonomy.add(reference.getDbname());
                } else if (reference.getDbkey().equalsIgnoreCase("ensembl")) {
                    ensembl.add(reference.getDbname());
                } else if (reference.getDbkey().equalsIgnoreCase("uniprot")) {
                    uniport.add(reference.getDbname());
                } else if (reference.getDbkey().equalsIgnoreCase("chebi")) {
                    chebi.add(reference.getDbname());
                } else if (reference.getDbkey().equalsIgnoreCase("pubmed")) {
                    pubmed.add(reference.getDbname());
                } else {
                    if (null == additionalMap.get(reference.getDbkey())) {
                        List<String> listForMap = new ArrayList<>();
                        listForMap.add(reference.getDbname());
                        additionalMap.put(reference.getDbkey(), listForMap);
                    } else {
                        List<String> listField = additionalMap.get(reference.getDbkey());
                        listField.add(reference.getDbname());
                        additionalMap.put(reference.getDbkey(), listField);
                    }
                }
            });

            solrEntry.setTaxonomy(taxonomy);
            solrEntry.setENSEMBL(ensembl);
            solrEntry.setUNIPROT(uniport);
            solrEntry.setCHEBI(chebi);
            solrEntry.setPUBMED(pubmed);

            List<String> submitterAffiliation = new ArrayList<>();
            List<String> instrumentPlatform = new ArrayList<>();

            List<String> model = new ArrayList<>();
            List<String> submission = new ArrayList<>();
            List<String> cellType = new ArrayList<>();
            List<String> additionalAccession = new ArrayList<>();
            List<String> ptmModification = new ArrayList<>();
            List<String> studyFactor = new ArrayList<>();
            List<String> technologyType = new ArrayList<>();
            List<String> proteomexchangeTypeSubmission = new ArrayList<>();
            List<String> pubchemId = new ArrayList<>();
            List<String> metaboliteName = new ArrayList<>();
            List<String> proteinName = new ArrayList<>();
            List<String> funding = new ArrayList<>();
            List<String> datasetType = new ArrayList<>();
            List<String> geneName = new ArrayList<>();


            List<String> tissues = new ArrayList<>();
            List<String> diseases = new ArrayList<>();
            List<String> omicsTypes = new ArrayList<>();
            List<String> submitterKeywords = new ArrayList<>();
            List<String> curatorKeywords = new ArrayList<>();
            List<String> datasetFiles = new ArrayList<>();
            List<String> softwares = new ArrayList<>();
            List<String> fulldatasetLinks = new ArrayList<>();
            List<String> repositories = new ArrayList<>();
            List<String> submitterEmails = new ArrayList<>();
            List<String> submitters = new ArrayList<>();
            List<String> species = new ArrayList<>();

            List<String> secondaryAccession = new ArrayList<>();
            List<String> pubmedAuthors = new ArrayList<>();

            entry.getAdditionalFields().getField().forEach(field -> {
                switch (field.getName()) {
                    case "pubmed_abstract":
                        solrEntry.setPubmedAbstract(field.getValue());
                        break;
                    case "dataset_type":
                        datasetType.add(field.getValue());
                        break;
                    case "gene_name":
                        geneName.add(field.getValue());
                        break;
                    case "funding":
                        funding.add(field.getValue());
                        break;
                    case "protein_name":
                        proteinName.add(field.getValue());
                        break;
                    case "metabolite_name":
                        metaboliteName.add(field.getValue());
                        break;
                    case "pubchem_id":
                        pubchemId.add(field.getValue());
                        break;
                    case "proteomexchange_type_submission":
                        proteomexchangeTypeSubmission.add(field.getValue());
                        break;
                    case "technology_type":
                        technologyType.add(field.getValue());
                        break;
                    case "ptm_modification":
                        ptmModification.add(field.getValue());
                        break;
                    case "study_factor":
                        studyFactor.add(field.getValue());
                        break;
                    case "file_count":
                        solrEntry.setFileCount(field.getValue());
                        break;
                    case "file_size":
                        solrEntry.setFileSize(field.getValue());
                        break;
                    case "additional_accession":
                        additionalAccession.add(field.getValue());
                        break;
                    case "cell_type":
                        cellType.add(field.getValue());
                        break;
                    case "submission":
                        submission.add(field.getValue());
                        break;
                    case "model":
                        model.add(field.getValue());
                        break;
                    case "submitter_affiliation":
                        submitterAffiliation.add(field.getValue());
                        break;
                    case "instrument_platform":
                        instrumentPlatform.add(field.getValue());
                        break;
                    case "view_count":
                        solrEntry.setViewCount(field.getValue());
                        break;
                    case "citation_count":
                        solrEntry.setCitationCount(field.getValue());
                        break;
                    case "search_count":
                        solrEntry.setSearchCount(field.getValue());
                        break;
                    case "reanalysis_count":
                        solrEntry.setReanalysisCount(field.getValue());
                        break;
                    case "tissue":
                        tissues.add(field.getValue());
                        break;
                    case "disease":
                        diseases.add(field.getValue());
                        break;
                    case "omics_type":
                        omicsTypes.add(field.getValue());
                        break;
                    case "submitter_keywords":
                        submitterKeywords.add(field.getValue());
                        break;
                    case "curator_keywords":
                        curatorKeywords.add(field.getValue());
                        break;
                    case "data_protocol":
                        solrEntry.setDataProtocol(field.getValue());
                        break;
                    case "sample_protocol":
                        solrEntry.setSampleProtocol(field.getValue());
                        break;
                    //????
                    case "normalized_connections":
                        solrEntry.setNormalizedConnections(field.getValue());
                        break;
                    case "download_count_scaled":
                        solrEntry.setDownloadCountScaled(field.getValue());
                        break;
                    case "citation_count_scaled":
                        solrEntry.setCitationCountScaled(field.getValue());
                        break;
                    case "reanalysis_count_scaled":
                        solrEntry.setReanalysisCountScaled(field.getValue());
                        break;
                    case "view_count_scaled":
                        solrEntry.setViewCountScaled(field.getValue());
                        break;
                    case "dataset_file":
                        datasetFiles.add(field.getValue());
                        break;
                    case "software":
                        softwares.add(field.getValue());
                        break;
                    case "full_dataset_link":
                        fulldatasetLinks.add(field.getValue());
                        break;
                    case "download_count":
                        solrEntry.setDownloadCount(field.getValue());
                        break;
                    case "sample_synonyms":
                        solrEntry.setSampleSynonyms(field.getValue());
                        break;
                    case "data_synonyms":
                        solrEntry.setDataSynonyms(field.getValue());
                        break;
                    case "name_synonyms":
                        solrEntry.setNameSynonyms(field.getValue());
                        break;
                    case "description_synonyms":
                        solrEntry.setDescriptionSynonyms(field.getValue());
                        break;
                    case "repository":
                        repositories.add(field.getValue());
                        break;
                    case "submitter_email":
                        submitterEmails.add(field.getValue());
                        break;
                    case "submitter":
                        submitters.add(field.getValue());
                        break;
                    case "species":
                        species.add(field.getValue());
                        break;
                    case "secondary_accession":
                        secondaryAccession.add(field.getValue());
                        break;
                    case "pubmed_title":
                        solrEntry.setPubmedTitle(field.getValue());
//                        pubmedTitles.add(field.getValue());
                        break;
                    case "pubmed_authors":
                        pubmedAuthors.add(field.getValue());
                        break;
                    case "pubmed_title_synonyms":
                        solrEntry.setPubmedTitleSynonyms(field.getValue());
                        break;
                    case "pubmed_abstract_synonyms":
                        solrEntry.setPubmedAbstractSynonyms(field.getValue());
                        break;
                    //?????
                    default:
                        if (null == additionalMap.get(field.getName())) {
                            List<String> listForMap = new ArrayList<>();
                            listForMap.add(field.getValue());
                            additionalMap.put(field.getName(), listForMap);
                        } else {
                            List<String> listField = additionalMap.get(field.getName());
                            listField.add(field.getValue());
                            additionalMap.put(field.getName(), listField);
                        }
                        break;
                }
            });


            solrEntry.setSubmitterAffiliation(submitterAffiliation);
            solrEntry.setInstrumentPlatform(instrumentPlatform);

            solrEntry.setPubchemId(pubchemId);
            solrEntry.setMetaboliteName(metaboliteName);
            solrEntry.setProteinName(proteinName);
            solrEntry.setFunding(funding);

            solrEntry.setDatasetType(datasetType);
            solrEntry.setGeneName(geneName);

            solrEntry.setModel(model);
            solrEntry.setSubmission(submission);
            solrEntry.setPtmModification(ptmModification);
            solrEntry.setStudyFactor(studyFactor);
            solrEntry.setTechnologyType(technologyType);
            solrEntry.setProteomexchangeTypeSubmission(proteomexchangeTypeSubmission);

            solrEntry.setCellType(cellType);

            solrEntry.setAdditionalAccession(additionalAccession);

            solrEntry.setDatasetFile(datasetFiles);
            solrEntry.setSoftware(softwares);
            solrEntry.setFullDatasetLink(fulldatasetLinks);


            solrEntry.setRepository(repositories);
            solrEntry.setSubmitterEmail(submitterEmails);
            solrEntry.setSubmitterMail(submitterEmails);
            solrEntry.setSubmitter(submitters);
            solrEntry.setSpecies(species);
            solrEntry.setSecondaryAccession(secondaryAccession);
//            solrEntry.setPubmedTitle(pubmedTitles);
            solrEntry.setPubmedAuthors(pubmedAuthors);

            solrEntry.setTissue(tissues);
            solrEntry.setDisease(diseases);
            solrEntry.setOmicsType(omicsTypes);
            solrEntry.setSubmitterKeywords(submitterKeywords);
            solrEntry.setCuratorKeywords(curatorKeywords);
//            solrEntry.setAdditionalFields(additionalMap);
            if (StringUtils.isBlank(solrEntry.getPubmedAbstract())) {
                solrEntry.setPubmedAbstract("Not Availiable");
            }
//            solrEntries.add(solrEntry);
            SolrInputDocument solrInputDocument = solrClient.getBinder().toSolrInputDocument(solrEntry);
            if (additionalMap.size() > 0) {
               additionalMap.keySet().forEach(key -> {
                   List<String> datas = additionalMap.get(key);
                   if(currentFieldName.stream().noneMatch(name -> name.equalsIgnoreCase(key))) {
                       autoGenerateField(key, core);
                       autoGenerateField(key, "omics");
                       currentFieldName.add(key);
                       solrInputDocument.addField(key, datas);
                   }else {
                       solrInputDocument.addField(key, datas);
                   }
               });
            }
            solrInputDocumentList.add(solrInputDocument);

        });

        return solrInputDocumentList;
    }
}
