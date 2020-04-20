package com.rainier.model;

public class Banner {
    private Integer id;
    private String imgUrl;
    private Integer bannerTime;
    private Integer sort;
    private Integer state;

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

    public Integer getBannerTime() {
        return bannerTime;
    }

    public void setBannerTime(Integer bannerTime) {
        this.bannerTime = bannerTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
