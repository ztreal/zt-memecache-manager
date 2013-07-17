package com.zt.memcache.domain.config;

/**
 * memcache基本元素属性.
 * User: zhangtan
 * Date: 13-7-15
 * Time: 下午4:21
 */
public class MemDat {
    public String ip;
    public int port;
    public String memKey;
    public String memValue;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMemKey() {
        return memKey;
    }

    public void setMemKey(String memKey) {
        this.memKey = memKey;
    }

    public String getMemValue() {
        return memValue;
    }

    public void setMemValue(String memValue) {
        this.memValue = memValue;
    }
}
