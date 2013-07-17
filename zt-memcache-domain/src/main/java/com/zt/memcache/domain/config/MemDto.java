package com.zt.memcache.domain.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台页面dto.
 * User: zhangtan
 * Date: 13-7-15
 * Time: 下午4:22
 */
public class MemDto {
    List<MemDat>  memDatLIst = new ArrayList<MemDat>();

    public List<MemDat> getMemDatLIst() {
        return memDatLIst;
    }

    public void setMemDatLIst(List<MemDat> memDatLIst) {
        this.memDatLIst = memDatLIst;
    }
}
