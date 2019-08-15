package cn.ncbsp.omicsdi.solr.queryModel;

/**
 * @author Xpon
 */ /*
    现在暂时只支持Q查询和facet查询
    以后再增加fq等属性

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
//    Map<String,List<String>> q;
//    List<String> facet;
//
//    public Map<String, List<String>> getQ() {
//        return q;
//    }
//
//    public void setQ(Map<String, List<String>> q) {
//        this.q = q;
//    }
//
//    public List<String> getFacet() {
//        return facet;
//    }
//
//    public void setFacet(List<String> facet) {
//        this.facet = facet;
//    }
public class QueryModel {
    String domain;
    String query;
    String[] fields;
    int start;
    int size;
    int facetcount;
    String sortfield;
    String order;
    String format;
    String sort;
    boolean fieldurl;
    boolean viewurl;
    String[] facets;
    String[] facetfields;
    int facetsdepth;
    String feedtitle;
    String feedmaxdays;
    String feedmaxdaysfield;
    String hlfields;
    String hlpretag;
    String hlposttag;
    String entryattrs;
    String[] entryids;
    String[] excludes;
    String excludesets;
    String entryid;
    String targetdomainid;
    String[] mltfields;
    int mintermfreq;
    int mindocfreq;
    int maxqueryterm;


    public int getMindocfreq() {
        return mindocfreq;
    }

    public void setMindocfreq(int mindocfreq) {
        this.mindocfreq = mindocfreq;
    }

    public int getMaxqueryterm() {
        return maxqueryterm;
    }

    public void setMaxqueryterm(int maxqueryterm) {
        this.maxqueryterm = maxqueryterm;
    }

    public int getMintermfreq() {
        return mintermfreq;
    }

    public void setMintermfreq(int mintermfreq) {
        this.mintermfreq = mintermfreq;
    }

    public String[] getMltfields() {
        return mltfields;
    }

    public void setMltfields(String[] mltfields) {
        this.mltfields = mltfields;
    }

    public String getTargetdomainid() {
        return targetdomainid;
    }

    public void setTargetdomainid(String targetdomainid) {
        this.targetdomainid = targetdomainid;
    }

    public String getEntryid() {
        return entryid;
    }

    public void setEntryid(String entryid) {
        this.entryid = entryid;
    }

    public String getExcludesets() {
        return excludesets;
    }

    public void setExcludesets(String excludesets) {
        this.excludesets = excludesets;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public String[] getEntryids() {
        return entryids;
    }

    public void setEntryids(String[] entryids) {
        this.entryids = entryids;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFacetcount() {
        return facetcount;
    }

    public void setFacetcount(int facetcount) {
        this.facetcount = facetcount;
    }

    public String getSortfield() {
        return sortfield;
    }

    public void setSortfield(String sortfield) {
        this.sortfield = sortfield;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean isFieldurl() {
        return fieldurl;
    }

    public void setFieldurl(boolean fieldurl) {
        this.fieldurl = fieldurl;
    }

    public boolean isViewurl() {
        return viewurl;
    }

    public void setViewurl(boolean viewurl) {
        this.viewurl = viewurl;
    }

    public String[] getFacets() {
        return facets;
    }

    public void setFacets(String[] facets) {
        this.facets = facets;
    }

    public String[] getFacetfields() {
        return facetfields;
    }

    public void setFacetfields(String[] facetfields) {
        this.facetfields = facetfields;
    }

    public int getFacetsdepth() {
        return facetsdepth;
    }

    public void setFacetsdepth(int facetsdepth) {
        this.facetsdepth = facetsdepth;
    }

    public String getFeedtitle() {
        return feedtitle;
    }

    public void setFeedtitle(String feedtitle) {
        this.feedtitle = feedtitle;
    }

    public String getFeedmaxdays() {
        return feedmaxdays;
    }

    public void setFeedmaxdays(String feedmaxdays) {
        this.feedmaxdays = feedmaxdays;
    }

    public String getFeedmaxdaysfield() {
        return feedmaxdaysfield;
    }

    public void setFeedmaxdaysfield(String feedmaxdaysfield) {
        this.feedmaxdaysfield = feedmaxdaysfield;
    }

    public String getHlfields() {
        return hlfields;
    }

    public void setHlfields(String hlfields) {
        this.hlfields = hlfields;
    }

    public String getHlpretag() {
        return hlpretag;
    }

    public void setHlpretag(String hlpretag) {
        this.hlpretag = hlpretag;
    }

    public String getHlposttag() {
        return hlposttag;
    }

    public void setHlposttag(String hlposttag) {
        this.hlposttag = hlposttag;
    }

    public String getEntryattrs() {
        return entryattrs;
    }

    public void setEntryattrs(String entryattrs) {
        this.entryattrs = entryattrs;
    }
}
