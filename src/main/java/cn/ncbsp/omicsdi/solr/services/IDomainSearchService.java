package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.queryModel.QueryModel;
import cn.ncbsp.omicsdi.solr.solrmodel.QueryResult;


/**
 * @author Xpon
 */
public interface IDomainSearchService {

    /**
     * @param queryModel
     * @return
     */
    /*
            @PathVariable(value = "domain") String domain,
            @RequestParam(value = "query",required = false, defaultValue = "") String query,
            @RequestParam(value = "fields",required = false, defaultValue = "") String fields,
            @RequestParam(value = "start",required = false, defaultValue = "0") int start,
            @RequestParam(value = "size",required = false, defaultValue = "10") int size,
            @RequestParam(value = "facetcount",required = false, defaultValue = "") int facetcount,
            @RequestParam(value = "sortfield",required = false, defaultValue = "") String sortfield,
            @RequestParam(value = "order",required = false, defaultValue = "") String order,
            @RequestParam(value = "format",required = false, defaultValue = "JSON") String format,
            @RequestParam(value = "sort",required = false, defaultValue = "") String sort,
            @RequestParam(value = "fieldurl",required = false, defaultValue = "") boolean fieldurl,
            @RequestParam(value = "viewurl",required = false, defaultValue = "") boolean viewurl,
            @RequestParam(value = "facets",required = false, defaultValue = "") String facets,
            @RequestParam(value = "facetfields",required = false, defaultValue = "") String facetfields,
            @RequestParam(value = "facetsdepth",required = false, defaultValue = "") int facetsdepth,
            @RequestParam(value = "feedtitle",required = false, defaultValue = "") String feedtitle,
            @RequestParam(value = "feedmaxdays",required = false, defaultValue = "") String feedmaxdays,
            @RequestParam(value = "feedmaxdaysfield",required = false, defaultValue = "") String feedmaxdaysfield,
            @RequestParam(value = "hlfields",required = false, defaultValue = "") String hlfields,
            @RequestParam(value = "hlpretag",required = false, defaultValue = "") String hlpretag,
            @RequestParam(value = "hlposttag",required = false, defaultValue = "") String hlposttag,
            @RequestParam(value = "entryattrs",required = false, defaultValue = "") String entryattrs
     */
    public QueryResult getQueryResult(QueryModel queryModel);
}
