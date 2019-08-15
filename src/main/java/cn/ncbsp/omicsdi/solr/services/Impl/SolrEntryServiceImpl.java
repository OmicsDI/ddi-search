package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.controller.Constans;
import cn.ncbsp.omicsdi.solr.model.Database;
import cn.ncbsp.omicsdi.solr.model.Entries;
import cn.ncbsp.omicsdi.solr.model.Entry;
import cn.ncbsp.omicsdi.solr.queryModel.FacetQueryModel;
import cn.ncbsp.omicsdi.solr.queryModel.IQModel;
import cn.ncbsp.omicsdi.solr.queryModel.QueryModel;
import cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder;
import cn.ncbsp.omicsdi.solr.repo.SolrEntryRepo;
import cn.ncbsp.omicsdi.solr.services.ISolrEntryService;
import cn.ncbsp.omicsdi.solr.solrmodel.*;
import cn.ncbsp.omicsdi.solr.util.SolrEntryUtil;
import cn.ncbsp.omicsdi.solr.util.XmlHelper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
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
    private final
    SolrEntryRepo solrEntryRepo;

    private final
    SolrClient solrClient;

    @Autowired
    public SolrEntryServiceImpl(SolrEntryRepo solrEntryRepo, SolrClient solrClient) {
        this.solrEntryRepo = solrEntryRepo;
        this.solrClient = solrClient;
    }

    @Override
    public void saveSolrEntry(String xml, String core) {
        Database database = new Database();
        database = XmlHelper.xmlToObject(xml, database);
        assert database != null;
        Entries entries = database.getEntries();
        List<Entry> list = entries.getEntry();
        List<SolrEntry> listSolrEntry = SolrEntryUtil.parseEntryToSolrEntry(list, database.getName().toLowerCase());
        solrEntryRepo.saveEntryList(core, listSolrEntry);
    }

    @Override
    public void saveSolrEntries(String folderPath, String core, String backupPath) {
        File folder = new File(folderPath);
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files.length > 0) {
                for (File file : files) {
                    if (Pattern.matches(".*.xml", file.getName()) || Pattern.matches(".*.XML", file.getName())) {
                        /*
                        可以把所有的solrEntry给加到列表里，但是我总觉得占用内存太大，不如用一次再说下一次
                         */
                        this.saveSolrEntry(file.getAbsolutePath(), core);
                        // 应该是移动到别的文件夹下留档
                        file.renameTo(new File(backupPath + "\\" + file.getName()));
                    } else {
                        continue;
                    }

                }
            }
        }
    }


    @Override
    public void saveSolrEntry(String xml) {
        Database database = new Database();
        database = XmlHelper.xmlToObject(xml, database);

        assert database != null;
        String core = Constans.Database.retriveSorlName(database.getName());
        Entries entries = database.getEntries();
        List<Entry> list = entries.getEntry();
        List<SolrEntry> listSolrEntry = SolrEntryUtil.parseEntryToSolrEntry(list, database.getName().toLowerCase());
        solrEntryRepo.saveEntryList("omics", listSolrEntry);
        solrEntryRepo.saveEntryList(core, listSolrEntry);
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
                        file.renameTo(new File(backupPath + "\\" + file.getName()));
                    } else {
                        continue;
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
    public QueryResult getQueryResult(String domain, IQModel iqModel) {


        SolrQuery solrQuery = SolrQueryBuilder.buildSolrQuery(iqModel);

        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(domain, solrQuery);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        QueryResult queryResult = new QueryResult();
        assert queryResponse != null;
        queryResult.setCount(queryResponse.getResults().size());

        cn.ncbsp.omicsdi.solr.solrmodel.Entry[] entries = queryResponse.getResults().stream().map(x -> {
            cn.ncbsp.omicsdi.solr.solrmodel.Entry entry = new cn.ncbsp.omicsdi.solr.solrmodel.Entry();
            entry.setScore(null);
            entry.setId((String) x.get("id"));
            entry.setSource((String) x.get("database"));
            Set<String> keys = x.getFieldValueMap().keySet();
            Map<String, String[]> map = new HashMap<>();
            Set<String> set = x.getFieldValueMap().keySet();
            for (String key : keys) {
                if (x.get(key) instanceof String) {
                    String str = (String) x.get(key);
                    map.put(key, new String[]{str});
                } else if (x.get(key) instanceof Long) {
                    String str = String.valueOf(x.get(key));
                    map.put(key, new String[]{str});
                } else if (x.get(key) instanceof  Float) {
                    String str = String.valueOf(x.get(key));
                    map.put(key, new String[]{str});
                } else {
                    ArrayList<String> list = (ArrayList<String>) x.get(key);
                    String[] str = new String[list.size()];
                    str = list.toArray(str);
                    map.put(key, str);
                }
            }
            entry.setFields(map);
            return entry;
        }).toArray(cn.ncbsp.omicsdi.solr.solrmodel.Entry[]::new);
        queryResult.setEntries(entries);

        queryResult.setDomains(null);
        if(queryResponse.getFacetFields() != null) {
            Facet[] facets = queryResponse.getFacetFields().stream().map(x -> {
                Facet facet = new Facet();
                facet.setId(x.getName());
                facet.setLabel(x.getName());
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
                        List<NCBITaxonomy> ncbiTaxonomyList = getNCBITaxonomyData(Arrays.stream(facetValues).map(FacetValue::getLabel).toArray(String[]::new));
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
        return queryResult;
    }
}
