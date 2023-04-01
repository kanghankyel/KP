package com.kidsplace.kidsplace.commons;

public class NoticeVO {

    private int nNum;
    private String nCategory;
    private String nWriter;
    private String nTitle;
    private String nContent;
    private String nDate;
    private String nDelete;

    public int getnNum() {
        return nNum;
    }
    public void setnNum(int nNum) {
        this.nNum = nNum;
    }
    public String getnCategory() {
        return nCategory;
    }
    public void setnCategory(String nCategory) {
        this.nCategory = nCategory;
    }
    public String getnWriter() {
        return nWriter;
    }
    public void setnWriter(String nWriter) {
        this.nWriter = nWriter;
    }
    public String getnTitle() {
        return nTitle;
    }
    public void setnTitle(String nTitle) {
        this.nTitle = nTitle;
    }
    public String getnContent() {
        return nContent;
    }
    public void setnContent(String nContent) {
        this.nContent = nContent;
    }
    public String getnDate() {
        return nDate;
    }
    public void setnDate(String nDate) {
        this.nDate = nDate;
    }
    public String getnDelete() {
        return nDelete;
    }
    public void setnDelete(String nDelete) {
        this.nDelete = nDelete;
    }

    @Override
    public String toString() {
        return "NoticeVO{" +
                "nNum=" + nNum +
                ", nCategory='" + nCategory + '\'' +
                ", nWriter='" + nWriter + '\'' +
                ", nTitle='" + nTitle + '\'' +
                ", nContent='" + nContent + '\'' +
                ", nDate=" + nDate +
                ", nDelete='" + nDelete + '\'' +
                '}';
    }
}
