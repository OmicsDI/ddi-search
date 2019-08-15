package cn.ncbsp.omicsdi.solr.queryModel;

import org.apache.solr.common.params.TermsParams;

/**
 * @author Xpon
 */
public class TermsQueryModel extends SimpleQueryModel implements IQModel {
    public static final String REQUEST_HANDLER = "/" + TermsParams.TERMS;


    private String terms;
    private String terms_fl;
    private String terms_list;
    private String terms_limit;
    private String terms_lower;
    private String terms_lower_incl;
    private String terms_mincount;
    private String terms_maxcount;
    private String terms_prefix;
    private String terms_raw;
    private String terms_regex;
    private String terms_regex_flag;
    private String terms_stats;
    private String terms_sort;
    private String terms_ttf;
    private String terms_upper;
    private String terms_upper_incl;

    public TermsQueryModel() {
    }

    public static String getREQUEST_HANDLER() {
        return REQUEST_HANDLER;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getTerms_fl() {
        return terms_fl;
    }

    public void setTerms_fl(String terms_fl) {
        this.terms_fl = terms_fl;
    }

    public String getTerms_list() {
        return terms_list;
    }

    public void setTerms_list(String terms_list) {
        this.terms_list = terms_list;
    }

    public String getTerms_limit() {
        return terms_limit;
    }

    public void setTerms_limit(String terms_limit) {
        this.terms_limit = terms_limit;
    }

    public String getTerms_lower() {
        return terms_lower;
    }

    public void setTerms_lower(String terms_lower) {
        this.terms_lower = terms_lower;
    }

    public String getTerms_lower_incl() {
        return terms_lower_incl;
    }

    public void setTerms_lower_incl(String terms_lower_incl) {
        this.terms_lower_incl = terms_lower_incl;
    }

    public String getTerms_mincount() {
        return terms_mincount;
    }

    public void setTerms_mincount(String terms_mincount) {
        this.terms_mincount = terms_mincount;
    }

    public String getTerms_maxcount() {
        return terms_maxcount;
    }

    public void setTerms_maxcount(String terms_maxcount) {
        this.terms_maxcount = terms_maxcount;
    }

    public String getTerms_prefix() {
        return terms_prefix;
    }

    public void setTerms_prefix(String terms_prefix) {
        this.terms_prefix = terms_prefix;
    }

    public String getTerms_raw() {
        return terms_raw;
    }

    public void setTerms_raw(String terms_raw) {
        this.terms_raw = terms_raw;
    }

    public String getTerms_regex() {
        return terms_regex;
    }

    public void setTerms_regex(String terms_regex) {
        this.terms_regex = terms_regex;
    }

    public String getTerms_regex_flag() {
        return terms_regex_flag;
    }

    public void setTerms_regex_flag(String terms_regex_flag) {
        this.terms_regex_flag = terms_regex_flag;
    }

    public String getTerms_stats() {
        return terms_stats;
    }

    public void setTerms_stats(String terms_stats) {
        this.terms_stats = terms_stats;
    }

    public String getTerms_sort() {
        return terms_sort;
    }

    public void setTerms_sort(String terms_sort) {
        this.terms_sort = terms_sort;
    }

    public String getTerms_ttf() {
        return terms_ttf;
    }

    public void setTerms_ttf(String terms_ttf) {
        this.terms_ttf = terms_ttf;
    }

    public String getTerms_upper() {
        return terms_upper;
    }

    public void setTerms_upper(String terms_upper) {
        this.terms_upper = terms_upper;
    }

    public String getTerms_upper_incl() {
        return terms_upper_incl;
    }

    public void setTerms_upper_incl(String terms_upper_incl) {
        this.terms_upper_incl = terms_upper_incl;
    }
}
