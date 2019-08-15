package cn.ncbsp.omicsdi.solr.queryModel;

/**
 * @author Xpon
 */
public class SuggestQueryModel extends SimpleQueryModel implements IQModel {
    public static final String REQUEST_HANDLER = "/suggest";
    private String suggest_dictionary;
    private String suggest_q;
    private String suggest_count;
    private String suggest_cfq;
    private String suggest_build;
    private String suggest_reload;
    private String suggest_buildAll;
    private String suggest_reloadAll;

    public SuggestQueryModel() {
    }

    public static String getREQUEST_HANDLER() {
        return REQUEST_HANDLER;
    }

    public String getSuggest_dictionary() {
        return suggest_dictionary;
    }

    public void setSuggest_dictionary(String suggest_dictionary) {
        this.suggest_dictionary = suggest_dictionary;
    }

    public String getSuggest_q() {
        return suggest_q;
    }

    public void setSuggest_q(String suggest_q) {
        this.suggest_q = suggest_q;
    }

    public String getSuggest_count() {
        return suggest_count;
    }

    public void setSuggest_count(String suggest_count) {
        this.suggest_count = suggest_count;
    }

    public String getSuggest_cfq() {
        return suggest_cfq;
    }

    public void setSuggest_cfq(String suggest_cfq) {
        this.suggest_cfq = suggest_cfq;
    }

    public String getSuggest_build() {
        return suggest_build;
    }

    public void setSuggest_build(String suggest_build) {
        this.suggest_build = suggest_build;
    }

    public String getSuggest_reload() {
        return suggest_reload;
    }

    public void setSuggest_reload(String suggest_reload) {
        this.suggest_reload = suggest_reload;
    }

    public String getSuggest_buildAll() {
        return suggest_buildAll;
    }

    public void setSuggest_buildAll(String suggest_buildAll) {
        this.suggest_buildAll = suggest_buildAll;
    }

    public String getSuggest_reloadAll() {
        return suggest_reloadAll;
    }

    public void setSuggest_reloadAll(String suggest_reloadAll) {
        this.suggest_reloadAll = suggest_reloadAll;
    }
}
