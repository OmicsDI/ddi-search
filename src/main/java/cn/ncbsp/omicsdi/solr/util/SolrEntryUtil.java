package cn.ncbsp.omicsdi.solr.util;

import cn.ncbsp.omicsdi.solr.model.Entry;
import cn.ncbsp.omicsdi.solr.solrmodel.SolrEntry;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolrEntryUtil {
    public static List<SolrEntry> parseEntryToSolrEntry(List<Entry> list, String database) {
        List<SolrEntry> solrEntries = new ArrayList<SolrEntry>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
                            try {
                                solrEntry.setPublicationDate(simpleDateFormat.parse(date.getValue()));
                            } catch (ParseException e) {
                                e.printStackTrace();
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
            solrEntry.setDatabase(database);
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
                    if (null == additionalMap.get("additional_" + reference.getDbkey())) {
                        List<String> listForMap = new ArrayList<>();
                        listForMap.add(reference.getDbname());
                        additionalMap.put("additional_" + reference.getDbkey(), listForMap);
                    } else {
                        List<String> listField = additionalMap.get("additional_" + reference.getDbkey());
                        listField.add(reference.getDbname());
                        additionalMap.put("additional_" + reference.getDbkey(), listField);
                    }
                }
            });

            solrEntry.setTaxonomy(taxonomy);
            solrEntry.setENSEMBL(ensembl);
            solrEntry.setUNIPROT(uniport);
            solrEntry.setCHEBI(chebi);
            solrEntry.setPUBMED(pubmed);

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
            List<String> pubmedTitles = new ArrayList<>();
            List<String> pubmedAuthors = new ArrayList<>();

            entry.getAdditionalFields().getField().forEach(field -> {
                switch (field.getName()) {
                    case "pubmed_abstract":
                        solrEntry.setPubmedAbstract(field.getValue());
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
                        if (null == additionalMap.get("additional_" + field.getName())) {
                            List<String> listForMap = new ArrayList<>();
                            listForMap.add(field.getValue());
                            additionalMap.put("additional_" + field.getName(), listForMap);
                        } else {
                            List<String> listField = additionalMap.get("additional_" + field.getName());
                            listField.add(field.getValue());
                            additionalMap.put("additional_" + field.getName(), listField);
                        }
                        break;
                }
            });


            solrEntry.setDatasetFile(datasetFiles);
            solrEntry.setSoftware(softwares);
            solrEntry.setFullDatasetLink(fulldatasetLinks);


            solrEntry.setRepository(repositories);
            solrEntry.setSubmitterEmail(submitterEmails);
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
            solrEntry.setAdditionalFields(additionalMap);
            if (StringUtils.isBlank(solrEntry.getPubmedAbstract())) {
                solrEntry.setPubmedAbstract("Not Availiable");
            }
            solrEntries.add(solrEntry);
        });

        return solrEntries;
    }
}
