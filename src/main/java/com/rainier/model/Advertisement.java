package com.rainier.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Advertisement {

    private Integer id;
    private String imgUrl;
    private String advUrl;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date expire;
    private Integer advState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAdvUrl() {
        return advUrl;
    }

    public void setAdvUrl(String advUrl) {
        this.advUrl = advUrl;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Integer getAdvState() {
        return advState;
    }

    public void setAdvState(Integer advState) {
        this.advState = advState;
    }
}
