package cn.ncbsp.omicsdi.solr.solrmodel;

public class DataDate {
    private String year;
    private String month;
    private String day;

    public DataDate(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public DataDate() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + " " + month + " " + day;
    }
}
