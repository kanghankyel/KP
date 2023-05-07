package com.kidsplace.kidsplace.commons;

public class TicketVO {

    private int tNum;
    private int uNum;
    private int tType;
    private String tName;
    private String uPhoneNum;
    private String uName;
    private String market;
    private String tArea;
    private String sellDate;
    private String useDate;
    private String used;
    // 티켓수량 vo객체 추가
    private int tCount;

    public TicketVO(){

    }
    public TicketVO(int tNum, int uNum, int tType, String tName, String uPhoneNum, String uName, String market, String tArea, String sellDate,
            String useDate, String used, int tCount) {
        this.tNum = tNum;
        this.uNum = uNum;
        this.tType = tType;
        this.tName = tName;
        this.uPhoneNum = uPhoneNum;
        this.uName = uName;
        this.market = market;
        this.tArea = tArea;
        this.sellDate = sellDate;
        this.useDate = useDate;
        this.used = used;
        this.tCount = tCount;
    }
    
    public int gettNum() {
        return tNum;
    }
    public void settNum(int tNum) {
        this.tNum = tNum;
    }
    public int getuNum() {
        return uNum;
    }
    public void setuNum(int uNum) {
        this.uNum = uNum;
    }
    public int gettType() {
        return tType;
    }
    public void settType(int tType) {
        this.tType = tType;
    }
    public String gettName() {
        return tName;
    }
    public void settName(String tName) {
        this.tName = tName;
    }
    public String getuPhoneNum() {
        return uPhoneNum;
    }
    public void setuPhoneNum(String uPhoneNum) {
        this.uPhoneNum = uPhoneNum;
    }
    public String getuName() {
        return uName;
    }
    public void setuName(String uName) {
        this.uName = uName;
    }
    public String getMarket() {
        return market;
    }
    public void setMarket(String market) {
        this.market = market;
    }
    public String gettArea() {
        return tArea;
    }
    public void settArea(String tArea) {
        this.tArea = tArea;
    }
    public String getSellDate() {
        return sellDate;
    }
    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }
    public String getUseDate() {
        return useDate;
    }
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }
    public String getUsed() {
        return used;
    }
    public void setUsed(String used) {
        this.used = used;
    }
    public int gettCount() {
        return tCount;
    }
    public void settCount(int tCount) {
        this.tCount = tCount;
    }

    @Override
    public String toString() {
        return "TicketVO [tNum=" + tNum + ", uNum=" + uNum + ", tType=" + tType + ", tName=" + tName + ", uPhoneNum="
                + uPhoneNum + ", uName=" + uName + ", market=" + market + ", tArea=" + tArea + ", sellDate=" + sellDate
                + ", useDate=" + useDate + ", used=" + used + ", tCount=" + tCount + "]";
    }
   
}
