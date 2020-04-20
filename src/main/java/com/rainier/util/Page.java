package com.rainier.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Barcke on 2017/6/28.
 */
public final class Page<T> implements Serializable{

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer total;

    /**
     * 总记录数
     */
    private Integer totalRecords;

    /**
     * 返回到前端的数据集合
     */
    private List<T> list;

    public Integer getPageIndex() {
        if (pageIndex==null||pageIndex<1)
            pageIndex=1;
        if (total!=null&&total>0&&pageIndex>total)
            pageIndex=total;
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        if (pageSize==null)
            pageSize=20;
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        total=totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
        this.totalRecords = totalRecords;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
