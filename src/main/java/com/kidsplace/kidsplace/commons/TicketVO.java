package com.kidsplace.kidsplace.commons;

public class TicketVO {

    private String tNum;
    private String uNum;
    private String tType;
    private String tName;
    private String tPrice;
    private String uPhoneNum;
    private String uName;
    private String market;
    private String tArea;
    private String sellDate;
    private String pickDate;
    private String useDate;
    private String used;
    // 티켓수량 vo객체 추가
    private int tCount;
    // 검색일 vo객체 추가
    private String startDate;
    private String endDate;

    public TicketVO(){

    }
    
    public TicketVO(String tNum, String uNum, String tType, String tName, String tPrice, String uPhoneNum, String uName, String market, String tArea, String sellDate,
            String pickDate, String useDate, String used, int tCount, String startDate, String endDate) {
        this.tNum = tNum;
        this.uNum = uNum;
        this.tType = tType;
        this.tName = tName;
        this.tPrice = tPrice;
        this.uPhoneNum = uPhoneNum;
        this.uName = uName;
        this.market = market;
        this.tArea = tArea;
        this.sellDate = sellDate;
        this.pickDate = pickDate;
        this.useDate = useDate;
        this.used = used;
        this.tCount = tCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String gettNum() {
        return tNum;
    }
    public void settNum(String tNum) {
        this.tNum = tNum;
    }
    public String getuNum() {
        return uNum;
    }
    public void setuNum(String uNum) {
        this.uNum = uNum;
    }
    public String gettType() {
        return tType;
    }
    public void settType(String tType) {
        this.tType = tType;
    }
    public String gettName() {
        return tName;
    }
    public void settName(String tName) {
        this.tName = tName;
    }
    public String gettPrice() {
        return tPrice;
    }
    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
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
    public String getPickDate() {
        return pickDate;
    }
    public void setPickDate(String pickDate) {
        this.pickDate = pickDate;
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TicketVO [tNum=" + tNum + ", uNum=" + uNum + ", tType=" + tType + ", tName=" + tName + ", tPrice="
                + tPrice + ", uPhoneNum=" + uPhoneNum + ", uName=" + uName + ", market=" + market + ", tArea=" + tArea
                + ", sellDate=" + sellDate + ", pickDate=" + pickDate + ", useDate=" + useDate + ", used=" + used
                + ", tCount=" + tCount + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
   
    
}
