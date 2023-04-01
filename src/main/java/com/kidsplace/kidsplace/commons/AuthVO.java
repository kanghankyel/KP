package com.kidsplace.kidsplace.commons;

public class AuthVO {

    private String uId;
    private String uAuth;

    public AuthVO(String uId, String uAuth) {
        this.uId = uId;
        this.uAuth = uAuth;
    }
    
    public String getuId() {
        return uId;
    }
    public void setuId(String uId) {
        this.uId = uId;
    }
    public String getuAuth() {
        return uAuth;
    }
    public void setuAuth(String uAuth) {
        this.uAuth = uAuth;
    }

    @Override
    public String toString() {
        return "AuthVO{" +
                "uId='" + uId + '\'' +
                ", uAuth='" + uAuth + '\'' +
                '}';
    }
    
}
