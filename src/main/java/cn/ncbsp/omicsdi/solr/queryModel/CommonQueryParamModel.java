package cn.ncbsp.omicsdi.solr.queryModel;

/**
 * @author Xpon
 */
public class CommonQueryParamModel implements IQModel {
    private String defType;
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

    public CommonQueryParamModel() {
    }

    public String getDefType() {
        return defType;
    }

    public void setDefType(String defType) {
        this.defType = defType;
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
}
