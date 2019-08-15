package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.queryModel.FacetQueryModel;
import cn.ncbsp.omicsdi.solr.solrmodel.FacetList;

/**
 * @author Xpon
 */
public interface ISolrFacetService {
    //    FacetList getFacetEntriesByDomains(String core, Map<String,String[]> paramMap);
    FacetList getFacetEntriesByDomains(String core, FacetQueryModel facetQueryModel);
}
