package cn.ncbsp.omicsdi.solr.mongoRepo.mongoModel;

/**
 * Created by gaur on 10/09/17.
 */
public class Scores {


    private int citationCount;

    private int reanalysisCount;

    private int viewCount;

    private int searchCount;

    public int getCitationCount() {
        return citationCount;
    }

    public void setCitationCount(int citationCount) {
        this.citationCount = citationCount;
    }

    public int getReanalysisCount() {
        return reanalysisCount;
    }

    public void setReanalysisCount(int reanalysisCount) {
        this.reanalysisCount = reanalysisCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }


}
