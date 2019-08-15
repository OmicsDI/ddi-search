package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.queryModel.IQModel;
import cn.ncbsp.omicsdi.solr.queryModel.QueryModel;
import cn.ncbsp.omicsdi.solr.solrmodel.NCBITaxonomy;
import cn.ncbsp.omicsdi.solr.solrmodel.QueryResult;

import java.util.List;

/**
 * @author Xpon
 */
public interface ISolrEntryService<T> {
    void saveSolrEntry(String xml, String core);

    void saveSolrEntries(String folderPath, String core, String backupPath);

    void saveSolrEntry(String xml);

    void saveSolrEntries(String folderPath, String backupPath);

    List<T> getSolrEntries(String core, QueryModel queryModel, Class<T> clazz);

    QueryResult getQueryResult(String domain, IQModel iqModel);

    List<NCBITaxonomy> getNCBITaxonomyData(String... taxonId);
}
