package com.kidsplace.kidsplace.commons;

public class FaqVO {
    
    private String fNum;
    private String uNum;
    private String fTitle;
    private String fContent;
    private String fDate;
    private String fUpdate;
    private String fDeleteDate;
    private String fDelete;

    public FaqVO(){

    }

    public FaqVO(String fNum, String uNum, String fTitle, String fContent, String fDate, String fUpdate,
            String fDeleteDate, String fDelete) {
        this.fNum = fNum;
        this.uNum = uNum;
        this.fTitle = fTitle;
        this.fContent = fContent;
        this.fDate = fDate;
        this.fUpdate = fUpdate;
        this.fDeleteDate = fDeleteDate;
        this.fDelete = fDelete;
    }

    public String getfNum() {
        return fNum;
    }
    public void setfNum(String fNum) {
        this.fNum = fNum;
    }
    public String getuNum() {
        return uNum;
    }
    public void setuNum(String uNum) {
        this.uNum = uNum;
    }
    public String getfTitle() {
        return fTitle;
    }
    public void setfTitle(String fTitle) {
        this.fTitle = fTitle;
    }
    public String getfContent() {
        return fContent;
    }
    public void setfContent(String fContent) {
        this.fContent = fContent;
    }
    public String getfDate() {
        return fDate;
    }
    public void setfDate(String fDate) {
        this.fDate = fDate;
    }
    public String getfUpdate() {
        return fUpdate;
    }
    public void setfUpdate(String fUpdate) {
        this.fUpdate = fUpdate;
    }
    public String getfDeleteDate() {
        return fDeleteDate;
    }
    public void setfDeleteDate(String fDeleteDate) {
        this.fDeleteDate = fDeleteDate;
    }
    public String getfDelete() {
        return fDelete;
    }
    public void setfDelete(String fDelete) {
        this.fDelete = fDelete;
    }

    @Override
    public String toString() {
        return "FaqVO [fNum=" + fNum + ", uNum=" + uNum + ", fTitle=" + fTitle + ", fContent=" + fContent + ", fDate="
                + fDate + ", fUpdate=" + fUpdate + ", fDeleteDate=" + fDeleteDate + ", fDelete=" + fDelete + "]";
    }

}
