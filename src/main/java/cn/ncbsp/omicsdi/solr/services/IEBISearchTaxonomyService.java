package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.solrmodel.NCBITaxonomy;

import java.util.List;

public interface IEBISearchTaxonomyService {
    List<NCBITaxonomy> getNCBITaxonomyData(String ...id);
}
