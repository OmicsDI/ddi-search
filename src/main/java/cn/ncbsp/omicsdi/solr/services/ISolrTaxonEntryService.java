package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.queryModel.IQModel;
import cn.ncbsp.omicsdi.solr.solrmodel.QueryResult;

public interface ISolrTaxonEntryService {
    public QueryResult getTaxonomy(String domain, IQModel iqModel);
}
