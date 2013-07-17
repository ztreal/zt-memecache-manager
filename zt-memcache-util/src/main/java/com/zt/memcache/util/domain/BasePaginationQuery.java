package com.zt.memcache.util.domain;

import java.util.Date;

/**
 * 分页查询基础类.
 * User: zhangtan
 * Date: 13-6-8
 * Time: 下午11:17
 */
public class BasePaginationQuery {
    /**
     * 结果集大小
     */
    private int rsnumber;

    /**
     * 起始记录号
     */
    private int start;

    /**
     * 终止记录号
     */
    private int end;

    /**
     * 起止时间
     */
    private Date startDate;

    public int getRsnumber() {
        return rsnumber;
    }

    public void setRsnumber(int rsnumber) {
        this.rsnumber = rsnumber;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
