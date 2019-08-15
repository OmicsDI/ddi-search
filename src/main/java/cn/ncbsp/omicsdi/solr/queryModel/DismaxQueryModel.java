package cn.ncbsp.omicsdi.solr.queryModel;

/**
 * @author Xpon
 */
public class DismaxQueryModel implements IQModel {
    private String q;
    private String q_alt;
    private String qf;
    private String mm;
    private String pf;
    private String ps;
    private String qs;
    private String tie;
    private String bq;
    private String bf;

    public DismaxQueryModel() {
    }


    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getQ_alt() {
        return q_alt;
    }

    public void setQ_alt(String q_alt) {
        this.q_alt = q_alt;
    }

    public String getQf() {
        return qf;
    }

    public void setQf(String qf) {
        this.qf = qf;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getQs() {
        return qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public String getTie() {
        return tie;
    }

    public void setTie(String tie) {
        this.tie = tie;
    }

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }
}
