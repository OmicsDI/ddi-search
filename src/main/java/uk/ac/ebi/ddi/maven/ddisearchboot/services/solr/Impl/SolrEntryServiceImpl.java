package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.ddi.maven.ddisearchboot.controller.Constans;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo.Database;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo.Entries;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo.Entry;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Facet;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.FacetValue;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.NCBITaxonomy;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.QueryResult;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.FacetQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.IQModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.QueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.SolrQueryBuilder;
import uk.ac.ebi.ddi.maven.ddisearchboot.repo.solr.SolrEntryRepo;
import uk.ac.ebi.ddi.maven.ddisearchboot.scheduler.ImportJob;
import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.IEBISearchTaxonomyService;
import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.ISolrEntryService;
import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.ISolrSchemaService;
import uk.ac.ebi.ddi.maven.ddisearchboot.util.XmlHelper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.CoreAdminParams;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Xpon
 */
@Service
public class SolrEntryServiceImpl implements ISolrEntryService {
    private static final Logger logger = LoggerFactory.getLogger(SolrEntryServiceImpl.class);

    private final
    SolrEntryRepo solrEntryRepo;

    private final
    SolrClient solrClient;

    private final ISolrSchemaService solrSchemaService;


    @Autowired
    IEBISearchTaxonomyService iebiSearchTaxonomyService;

    @Autowired
    public SolrEntryServiceImpl(SolrEntryRepo solrEntryRepo, SolrClient solrClient, ISolrSchemaService solrSchemaService) {
        this.solrEntryRepo = solrEntryRepo;
        this.solrClient = solrClient;
        this.solrSchemaService = solrSchemaService;
    }


    @Override
    public void saveSolrEntry(String xml) {
        Database database = new Database();
        database = XmlHelper.xmlToObject(xml, database);
        if (database == null) {
            return;
        }
        String core = Constans.Database.retriveSorlName(database.getName());
        Entries entries = database.getEntries();
        List<Entry> list = entries.getEntry();
        List<SolrInputDocument> solrInputDocuments = solrSchemaService.parseEntryToSolrInputDocument(list, database.getName().toLowerCase(), core);
        solrEntryRepo.saveEntryList("omics", solrInputDocuments);
        solrEntryRepo.saveEntryList(core, solrInputDocuments);
    }

    @Override
    public void saveSolrEntries(String folderPath, String backupPath) {
        File folder = new File(folderPath);
        if (folder.exists()) {
            File[] files = folder.listFiles();
            assert files != null;
            if (files.length > 0) {
                for (File file : files) {
                    if (Pattern.matches(".*.xml", file.getName()) || Pattern.matches(".*.XML", file.getName())) {
                        /*
                        可以把所有的solrEntry给加到列表里，但是我总觉得占用内存太大，不如用一次再说下一次
                         */
                        this.saveSolrEntry(file.getAbsolutePath());
                        // 应该是移动到别的文件夹下留档
                        file.renameTo(new File(backupPath + File.separator + file.getName()));
                    }

                }
            }
        }
    }

    @Override
    public List getSolrEntries(String core, QueryModel queryModel, Class clazz) {
//        solrEntryRepo.getQueryResult(core,query,clazz);
        return null;
    }

    @Override
    public List<NCBITaxonomy> getNCBITaxonomyData(String... taxonId) {
        SolrQuery solrQuery = new SolrQuery();
        StringBuilder stringBuffer = new StringBuilder();
        for (String s : taxonId) {
            if (stringBuffer.length() == 0) {
                stringBuffer.append(s);
            } else {
                stringBuffer.append(" OR ").append(s);
            }
        }


        solrQuery.setQuery("tax_id:(" + stringBuffer.toString() + ") AND name_class:\"scientific name\"");
        solrQuery.setRows(taxonId.length);
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query("taxonomy", solrQuery);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert queryResponse != null;
        return queryResponse.getResults().stream().map(x -> {
            NCBITaxonomy ncbiTaxonomy = new NCBITaxonomy();
            ncbiTaxonomy.setTaxId(String.valueOf(x.get("tax_id")));
            ncbiTaxonomy.setNameTxt(String.valueOf(x.get("name_txt")));
            ncbiTaxonomy.setUniqueName(String.valueOf(x.get("name_txt")));
            ncbiTaxonomy.setNameClass(String.valueOf(x.get("name_class")));
            return ncbiTaxonomy;
        }).collect(Collectors.toList());
    }

    @Override
    public QueryResult getQueryResult(String domain, IQModel iqModel, String order, String sortfield) {
        SolrQuery solrQuery = SolrQueryBuilder.buildSolrQuery(iqModel);
        SolrQueryBuilder.addSort(order, sortfield, solrQuery);
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(domain, solrQuery);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        QueryResult queryResult = new QueryResult();
        assert queryResponse != null;
        queryResult.setHitCount(queryResponse.getResults().size());

        uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Entry[] entries = queryResponse.getResults().stream().map(x -> {
            uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Entry entry = new uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Entry();
            entry.setScore(null);
            entry.setId((String) x.get("id"));
            entry.setSource((String) x.get("database"));
            Set<String> keys = x.getFieldValueMap().keySet();
            Map<String, String[]> map = new HashMap<>();
            Set<String> set = x.getFieldValueMap().keySet();
            for (String key : keys) {
                if (x.get(key) instanceof List) {
                    ArrayList<String> list = (ArrayList<String>) x.get(key);
                    String[] str = new String[list.size()];
                    str = list.toArray(str);
                    map.put(key, str);
                } else {
                    String str = String.valueOf(x.get(key));
                    map.put(key, new String[]{str});
                }
            }
            entry.setFields(map);
            return entry;
        }).toArray(uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Entry[]::new);
        queryResult.setEntries(entries);

        queryResult.setDomains(null);
        if(queryResponse.getFacetFields() != null) {
            Facet[] facets = queryResponse.getFacetFields().stream().map(x -> {
                Facet facet = new Facet();
                facet.setId(x.getName());
                facet.setLabel(makeFirstUpperCase(x.getName()));
                Long total = x.getValues().stream().mapToLong(FacetField.Count::getCount).sum();
                facet.setTotal(Math.toIntExact(total));
                FacetValue[] facetValues = x.getValues().stream().map(z -> {
                    FacetValue facetValue = new FacetValue();
                    facetValue.setValue(z.getName());
                    facetValue.setLabel(z.getName());
                    facetValue.setCount(String.valueOf(z.getCount()));
                    return facetValue;
                }).toArray(FacetValue[]::new);


                if(iqModel instanceof FacetQueryModel) {
                    FacetQueryModel facetQueryModel = (FacetQueryModel) iqModel;
                    if ("TAXONOMY".equalsIgnoreCase(facetQueryModel.getFacet_field())) {
                        List<NCBITaxonomy> ncbiTaxonomyList = iebiSearchTaxonomyService.getNCBITaxonomyData(Arrays.stream(facetValues).map(FacetValue::getLabel).toArray(String[]::new));
                        Map<String, String> map = new ConcurrentHashMap<>();
                        ncbiTaxonomyList.forEach(z -> map.put(z.getTaxId(), z.getNameTxt()));
                        for (FacetValue facetValue : facetValues) {
                            facetValue.setLabel(map.get(facetValue.getLabel()));
                        }
                    }
                }


                facet.setFacetValues(facetValues);
                facet.setTotal(Math.toIntExact(x.getValues().stream().mapToLong(FacetField.Count::getCount).sum()));

                facet.setFacetValues(facetValues);
                return facet;
            }).toArray(Facet[]::new);
            queryResult.setFacets(facets);
        }

        CoreAdminRequest coreAdminRequest = new CoreAdminRequest();
        coreAdminRequest.setAction(CoreAdminParams.CoreAdminAction.STATUS);
        CoreAdminResponse coreAdminResponse = null;
        try {
            coreAdminResponse = coreAdminRequest.process(solrClient);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert coreAdminResponse != null;
        NamedList<NamedList<Object>> coreStatus = coreAdminResponse.getCoreStatus();
        NamedList<Object> indexMap = (NamedList<Object>) coreStatus.get("omics").get("index");
        queryResult.setHitCount((Integer) indexMap.get("numDocs"));
        return queryResult;
    }


    private String makeFirstUpperCase(String string) {
        return string.substring(0,1).toUpperCase()+string.substring(1);
    }
}
