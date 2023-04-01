package com.kidsplace.kidsplace.commons;

import java.util.Date;

public class TicketDetailVO {
    
    private int tType;
    private String tName;
    private String tPrice;
    private Date tDate;
    private Date tUpdate;
    private Date tDeletedate;
    private String tDelete;

    public TicketDetailVO(){

    }
    public TicketDetailVO(int tType, String tName, String tPrice, Date tDate, Date tUpdate, Date tDeletedate,
            String tDelete) {
        this.tType = tType;
        this.tName = tName;
        this.tPrice = tPrice;
        this.tDate = tDate;
        this.tUpdate = tUpdate;
        this.tDeletedate = tDeletedate;
        this.tDelete = tDelete;
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
    public String gettPrice() {
        return tPrice;
    }
    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }
    public Date gettDate() {
        return tDate;
    }
    public void settDate(Date tDate) {
        this.tDate = tDate;
    }
    public Date gettUpdate() {
        return tUpdate;
    }
    public void settUpdate(Date tUpdate) {
        this.tUpdate = tUpdate;
    }
    public Date gettDeletedate() {
        return tDeletedate;
    }
    public void settDeletedate(Date tDeletedate) {
        this.tDeletedate = tDeletedate;
    }
    public String gettDelete() {
        return tDelete;
    }
    public void settDelete(String tDelete) {
        this.tDelete = tDelete;
    }

    @Override
    public String toString() {
        return "TicketDetailVO [tType=" + tType + ", tName=" + tName + ", tPrice=" + tPrice + ", tDate=" + tDate
                + ", tUpdate=" + tUpdate + ", tDeletedate=" + tDeletedate + ", tDelete=" + tDelete + "]";
    }

}
