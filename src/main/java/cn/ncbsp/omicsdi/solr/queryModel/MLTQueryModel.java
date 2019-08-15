package cn.ncbsp.omicsdi.solr.queryModel;

import org.apache.solr.common.params.MoreLikeThisParams;

/**
 * @author Xpon
 */
public class MLTQueryModel extends SimpleQueryModel implements IQModel {
    public static final String REQUEST_HANDLER = "/" + MoreLikeThisParams.MLT;

    public String mlt_fl;
    public String mlt_mintf;
    public String mlt_mindf;
    public String mlt_maxdf;
    public String mlt_maxdfpct;
    public String mlt_minwl;
    public String mlt_maxwl;
    public String mlt_maxqt;
    public String mlt_maxntp;
    public String mlt_boost;
    public String mlt_qf;
    public String mlt_match_include;
    public String mlt_match_offset;
    public String mlt_interestingTerms;


    public MLTQueryModel() {
        super();
    }

    public static String getREQUEST_HANDLER() {
        return REQUEST_HANDLER;
    }

    public String getMlt_fl() {
        return mlt_fl;
    }

    public void setMlt_fl(String mlt_fl) {
        this.mlt_fl = mlt_fl;
    }

    public String getMlt_mintf() {
        return mlt_mintf;
    }

    public void setMlt_mintf(String mlt_mintf) {
        this.mlt_mintf = mlt_mintf;
    }

    public String getMlt_mindf() {
        return mlt_mindf;
    }

    public void setMlt_mindf(String mlt_mindf) {
        this.mlt_mindf = mlt_mindf;
    }

    public String getMlt_maxdf() {
        return mlt_maxdf;
    }

    public void setMlt_maxdf(String mlt_maxdf) {
        this.mlt_maxdf = mlt_maxdf;
    }

    public String getMlt_maxdfpct() {
        return mlt_maxdfpct;
    }

    public void setMlt_maxdfpct(String mlt_maxdfpct) {
        this.mlt_maxdfpct = mlt_maxdfpct;
    }

    public String getMlt_minwl() {
        return mlt_minwl;
    }

    public void setMlt_minwl(String mlt_minwl) {
        this.mlt_minwl = mlt_minwl;
    }

    public String getMlt_maxwl() {
        return mlt_maxwl;
    }

    public void setMlt_maxwl(String mlt_maxwl) {
        this.mlt_maxwl = mlt_maxwl;
    }

    public String getMlt_maxqt() {
        return mlt_maxqt;
    }

    public void setMlt_maxqt(String mlt_maxqt) {
        this.mlt_maxqt = mlt_maxqt;
    }

    public String getMlt_maxntp() {
        return mlt_maxntp;
    }

    public void setMlt_maxntp(String mlt_maxntp) {
        this.mlt_maxntp = mlt_maxntp;
    }

    public String getMlt_boost() {
        return mlt_boost;
    }

    public void setMlt_boost(String mlt_boost) {
        this.mlt_boost = mlt_boost;
    }

    public String getMlt_qf() {
        return mlt_qf;
    }

    public void setMlt_qf(String mlt_qf) {
        this.mlt_qf = mlt_qf;
    }

    public String getMlt_match_include() {
        return mlt_match_include;
    }

    public void setMlt_match_include(String mlt_match_include) {
        this.mlt_match_include = mlt_match_include;
    }

    public String getMlt_match_offset() {
        return mlt_match_offset;
    }

    public void setMlt_match_offset(String mlt_match_offset) {
        this.mlt_match_offset = mlt_match_offset;
    }

    public String getMlt_interestingTerms() {
        return mlt_interestingTerms;
    }

    public void setMlt_interestingTerms(String mlt_interestingTerms) {
        this.mlt_interestingTerms = mlt_interestingTerms;
    }
}
