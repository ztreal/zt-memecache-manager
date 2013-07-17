package com.zt.memcache.domain.config;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: zhangtan
 * Date: 13-7-15
 * Time: 上午11:41
 */
public class MemcacheConfig implements Serializable {

    private ArrayList<InetSocketAddress> memcacheList;

    public ArrayList<InetSocketAddress> getMemcacheList() {
        return memcacheList;
    }

    public void setMemcacheList(ArrayList<InetSocketAddress> memcacheList) {
        this.memcacheList = memcacheList;
    }
}
