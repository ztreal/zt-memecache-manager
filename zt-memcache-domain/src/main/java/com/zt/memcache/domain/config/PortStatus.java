package com.zt.memcache.domain.config;

/**
 * Created with IntelliJ IDEA.
 * User: zhangtan
 * Date: 13-7-16
 * Time: 下午4:50
 */
public class PortStatus {
    private String statusDesc;
    private int statusCode;

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
