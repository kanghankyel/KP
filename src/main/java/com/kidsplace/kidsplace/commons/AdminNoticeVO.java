package com.kidsplace.kidsplace.commons;

public class AdminNoticeVO {
    
    private int aNum;
    private String aCategory;
    private String aWriter;
    private String aTitle;
    private String aContent;
    private String aDate;
    private String aDelete;


    public int getaNum() {
        return aNum;
    }
    public void setaNum(int aNum) {
        this.aNum = aNum;
    }
    public String getaCategory() {
        return aCategory;
    }
    public void setaCategory(String aCategory) {
        this.aCategory = aCategory;
    }
    public String getaWriter() {
        return aWriter;
    }
    public void setaWriter(String aWriter) {
        this.aWriter = aWriter;
    }
    public String getaTitle() {
        return aTitle;
    }
    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }
    public String getaContent() {
        return aContent;
    }
    public void setaContent(String aContent) {
        this.aContent = aContent;
    }
    public String getaDate() {
        return aDate;
    }
    public void setaDate(String aDate) {
        this.aDate = aDate;
    }
    public String getaDelete() {
        return aDelete;
    }
    public void setaDelete(String aDelete) {
        this.aDelete = aDelete;
    }


    @Override
    public String toString() {
        return "AdminNoticeVO [aNum=" + aNum + ", aCategory=" + aCategory + ", aWriter=" + aWriter + ", aTitle="
                + aTitle + ", aContent=" + aContent + ", aDate=" + aDate + ", aDelete=" + aDelete + "]";
    }


}
