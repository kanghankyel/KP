package com.kidsplace.kidsplace.commons;

import java.util.Date;

public class TicketVO {

    private int tNum;
    private int uNum;
    private int tType;
    private String uName;
    private String market;
    private String tArea;
    private Date sellDate;
    private Date useDate;
    private String used;

    public TicketVO(){

    }
    public TicketVO(int tNum, int uNum, int tType, String uName, String market, String tArea, Date sellDate,
            Date useDate, String used) {
        this.tNum = tNum;
        this.uNum = uNum;
        this.tType = tType;
        this.uName = uName;
        this.market = market;
        this.tArea = tArea;
        this.sellDate = sellDate;
        this.useDate = useDate;
        this.used = used;
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
    public Date getSellDate() {
        return sellDate;
    }
    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }
    public Date getUseDate() {
        return useDate;
    }
    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }
    public String getUsed() {
        return used;
    }
    public void setUsed(String used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "TicketVO [tNum=" + tNum + ", uNum=" + uNum + ", tType=" + tType + ", uName=" + uName + ", market="
                + market + ", tArea=" + tArea + ", sellDate=" + sellDate + ", useDate=" + useDate + ", used=" + used
                + "]";
    }
    
}
