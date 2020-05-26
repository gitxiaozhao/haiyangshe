package com.rainier.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Reply {
    private Integer id;
    private Integer problem_id;
    private Integer user_id;
    private String text;
    private Integer last_id;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date submit_time;
    private String reply_img;
    private Integer reply_state;
    private Integer adopt;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date examine_time;
    private String fail_reason;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date adopt_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLast_id() {
        return last_id;
    }

    public void setLast_id(Integer last_id) {
        this.last_id = last_id;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public String getReply_img() {
        return reply_img;
    }

    public void setReply_img(String reply_img) {
        this.reply_img = reply_img;
    }

    public Integer getReply_state() {
        return reply_state;
    }

    public void setReply_state(Integer reply_state) {
        this.reply_state = reply_state;
    }

    public Integer getAdopt() {
        return adopt;
    }

    public void setAdopt(Integer adopt) {
        this.adopt = adopt;
    }

    public Date getExamine_time() {
        return examine_time;
    }

    public void setExamine_time(Date examine_time) {
        this.examine_time = examine_time;
    }

    public String getFail_reason() {
        return fail_reason;
    }

    public void setFail_reason(String fail_reason) {
        this.fail_reason = fail_reason;
    }

    public Date getAdopt_time() {
        return adopt_time;
    }

    public void setAdopt_time(Date adopt_time) {
        this.adopt_time = adopt_time;
    }
}
