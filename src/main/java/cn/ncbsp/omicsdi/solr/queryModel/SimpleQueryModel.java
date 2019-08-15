package cn.ncbsp.omicsdi.solr.queryModel;

/**
 * 查询参数对象
 *
 * @author Xpon
 */
public class SimpleQueryModel implements IQModel {

    private String q;
    private String q_op;
    private String df;
    private String sow;
    private String sort;
    private String start;
    private String rows;
    private String fq;
    private String fl;
    private String debug;
    private String explainOther;
    private String timeAllowed;
    private String segmentTerminateEarly;
    private String omitHeader;
    private String wt;
    private String cache;
    private String logParamsList;
    private String echoParams;

    public SimpleQueryModel() {
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getExplainOther() {
        return explainOther;
    }

    public void setExplainOther(String explainOther) {
        this.explainOther = explainOther;
    }

    public String getTimeAllowed() {
        return timeAllowed;
    }

    public void setTimeAllowed(String timeAllowed) {
        this.timeAllowed = timeAllowed;
    }

    public String getSegmentTerminateEarly() {
        return segmentTerminateEarly;
    }

    public void setSegmentTerminateEarly(String segmentTerminateEarly) {
        this.segmentTerminateEarly = segmentTerminateEarly;
    }

    public String getOmitHeader() {
        return omitHeader;
    }

    public void setOmitHeader(String omitHeader) {
        this.omitHeader = omitHeader;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getLogParamsList() {
        return logParamsList;
    }

    public void setLogParamsList(String logParamsList) {
        this.logParamsList = logParamsList;
    }

    public String getEchoParams() {
        return echoParams;
    }

    public void setEchoParams(String echoParams) {
        this.echoParams = echoParams;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getQ_op() {
        return q_op;
    }

    public void setQ_op(String q_op) {
        this.q_op = q_op;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public String getSow() {
        return sow;
    }

    public void setSow(String sow) {
        this.sow = sow;
    }
}
