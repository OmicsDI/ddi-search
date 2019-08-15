package cn.ncbsp.omicsdi.solr.queryModel;

/**
 * 分面对象
 *
 * @author Xpon
 */

public class FacetQueryModel extends SimpleQueryModel implements IQModel {
    private String facet_query;
    private String facet_field;
    private String facet_prefix;
    private String facet_contains;
    private String facet_contains_ignoreCase;
    private String facet_matches;
    private String facet_sort;
    private String facet_limit;
    private String facet_offset;
    private String facet_mincount;
    private String facet_missing;
    private String facet_method;
    private String facet_enum_cache_minDf;
    private String facet_exists;
    private String facet_excludeTerms;
    private String facet_overrequest_count;
    private String facet_overrequest_ratio;
    private String facet_threads;
    private String facet_range;
    private String facet_range_start;
    private String facet_range_end;
    private String facet_range_gap;
    private String facet_range_hardend;
    private String facet_range_include;
    private String facet_range_other;
    private String facet_range_method;
    private String facet_pivot;
    private String facet_pivot_mincount;
    private String facet_interval;
    private String facet_interval_set;


    public FacetQueryModel() {
        super();
    }

    public String getFacet_query() {
        return facet_query;
    }

    public void setFacet_query(String facet_query) {
        this.facet_query = facet_query;
    }

    public String getFacet_field() {
        return facet_field;
    }

    public void setFacet_field(String facet_field) {
        this.facet_field = facet_field;
    }

    public String getFacet_prefix() {
        return facet_prefix;
    }

    public void setFacet_prefix(String facet_prefix) {
        this.facet_prefix = facet_prefix;
    }

    public String getFacet_contains() {
        return facet_contains;
    }

    public void setFacet_contains(String facet_contains) {
        this.facet_contains = facet_contains;
    }

    public String getFacet_contains_ignoreCase() {
        return facet_contains_ignoreCase;
    }

    public void setFacet_contains_ignoreCase(String facet_contains_ignoreCase) {
        this.facet_contains_ignoreCase = facet_contains_ignoreCase;
    }

    public String getFacet_matches() {
        return facet_matches;
    }

    public void setFacet_matches(String facet_matches) {
        this.facet_matches = facet_matches;
    }

    public String getFacet_sort() {
        return facet_sort;
    }

    public void setFacet_sort(String facet_sort) {
        this.facet_sort = facet_sort;
    }

    public String getFacet_limit() {
        return facet_limit;
    }

    public void setFacet_limit(String facet_limit) {
        this.facet_limit = facet_limit;
    }

    public String getFacet_offset() {
        return facet_offset;
    }

    public void setFacet_offset(String facet_offset) {
        this.facet_offset = facet_offset;
    }

    public String getFacet_mincount() {
        return facet_mincount;
    }

    public void setFacet_mincount(String facet_mincount) {
        this.facet_mincount = facet_mincount;
    }

    public String getFacet_missing() {
        return facet_missing;
    }

    public void setFacet_missing(String facet_missing) {
        this.facet_missing = facet_missing;
    }

    public String getFacet_method() {
        return facet_method;
    }

    public void setFacet_method(String facet_method) {
        this.facet_method = facet_method;
    }

    public String getFacet_enum_cache_minDf() {
        return facet_enum_cache_minDf;
    }

    public void setFacet_enum_cache_minDf(String facet_enum_cache_minDf) {
        this.facet_enum_cache_minDf = facet_enum_cache_minDf;
    }

    public String getFacet_exists() {
        return facet_exists;
    }

    public void setFacet_exists(String facet_exists) {
        this.facet_exists = facet_exists;
    }

    public String getFacet_excludeTerms() {
        return facet_excludeTerms;
    }

    public void setFacet_excludeTerms(String facet_excludeTerms) {
        this.facet_excludeTerms = facet_excludeTerms;
    }

    public String getFacet_overrequest_count() {
        return facet_overrequest_count;
    }

    public void setFacet_overrequest_count(String facet_overrequest_count) {
        this.facet_overrequest_count = facet_overrequest_count;
    }

    public String getFacet_overrequest_ratio() {
        return facet_overrequest_ratio;
    }

    public void setFacet_overrequest_ratio(String facet_overrequest_ratio) {
        this.facet_overrequest_ratio = facet_overrequest_ratio;
    }

    public String getFacet_threads() {
        return facet_threads;
    }

    public void setFacet_threads(String facet_threads) {
        this.facet_threads = facet_threads;
    }

    public String getFacet_range() {
        return facet_range;
    }

    public void setFacet_range(String facet_range) {
        this.facet_range = facet_range;
    }

    public String getFacet_range_start() {
        return facet_range_start;
    }

    public void setFacet_range_start(String facet_range_start) {
        this.facet_range_start = facet_range_start;
    }

    public String getFacet_range_end() {
        return facet_range_end;
    }

    public void setFacet_range_end(String facet_range_end) {
        this.facet_range_end = facet_range_end;
    }

    public String getFacet_range_gap() {
        return facet_range_gap;
    }

    public void setFacet_range_gap(String facet_range_gap) {
        this.facet_range_gap = facet_range_gap;
    }

    public String getFacet_range_hardend() {
        return facet_range_hardend;
    }

    public void setFacet_range_hardend(String facet_range_hardend) {
        this.facet_range_hardend = facet_range_hardend;
    }

    public String getFacet_range_include() {
        return facet_range_include;
    }

    public void setFacet_range_include(String facet_range_include) {
        this.facet_range_include = facet_range_include;
    }

    public String getFacet_range_other() {
        return facet_range_other;
    }

    public void setFacet_range_other(String facet_range_other) {
        this.facet_range_other = facet_range_other;
    }

    public String getFacet_range_method() {
        return facet_range_method;
    }

    public void setFacet_range_method(String facet_range_method) {
        this.facet_range_method = facet_range_method;
    }

    public String getFacet_pivot() {
        return facet_pivot;
    }

    public void setFacet_pivot(String facet_pivot) {
        this.facet_pivot = facet_pivot;
    }

    public String getFacet_pivot_mincount() {
        return facet_pivot_mincount;
    }

    public void setFacet_pivot_mincount(String facet_pivot_mincount) {
        this.facet_pivot_mincount = facet_pivot_mincount;
    }

    public String getFacet_interval() {
        return facet_interval;
    }

    public void setFacet_interval(String facet_interval) {
        this.facet_interval = facet_interval;
    }

    public String getFacet_interval_set() {
        return facet_interval_set;
    }

    public void setFacet_interval_set(String facet_interval_set) {
        this.facet_interval_set = facet_interval_set;
    }
}
