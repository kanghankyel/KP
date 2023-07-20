package com.kidsplace.kidsplace.commons;

public class IpVO {
    
    private String vIpAddress;
    private String vVisitTime;
    private int vCount;
    

    public IpVO(String vIpAddress, String vVisitTime, int vCount) {
        this.vIpAddress = vIpAddress;
        this.vVisitTime = vVisitTime;
        this.vCount = vCount;
    }


    public String getvIpAddress() {
        return vIpAddress;
    }
    public void setvIpAddress(String vIpAddress) {
        this.vIpAddress = vIpAddress;
    }
    public String getvVisitTime() {
        return vVisitTime;
    }
    public void setvVisitTime(String vVisitTime) {
        this.vVisitTime = vVisitTime;
    }
    public int getvCount() {
        return vCount;
    }
    public void setvCount(int vCount) {
        this.vCount = vCount;
    }


    @Override
    public String toString() {
        return "IpVO [vIpAddress=" + vIpAddress + ", vVisitTime=" + vVisitTime + ", vCount=" + vCount + "]";
    }
    

}
