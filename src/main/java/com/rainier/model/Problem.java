package com.rainier.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Problem {
    private Integer id;
    private Integer user_id;
    private String problem;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date submit_time;
    private Integer problem_state;
    private String problem_img;
    private Integer click;
    private Integer answer;
    private Integer problem_info;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date examine_time;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date answer_time;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date preserve_time;
    private String fail_reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public Integer getProblem_state() {
        return problem_state;
    }

    public void setProblem_state(Integer problem_state) {
        this.problem_state = problem_state;
    }

    public String getProblem_img() {
        return problem_img;
    }

    public void setProblem_img(String problem_img) {
        this.problem_img = problem_img;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public Integer getProblem_info() {
        return problem_info;
    }

    public void setProblem_info(Integer problem_info) {
        this.problem_info = problem_info;
    }

    public Date getExamine_time() {
        return examine_time;
    }

    public void setExamine_time(Date examine_time) {
        this.examine_time = examine_time;
    }

    public Date getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(Date answer_time) {
        this.answer_time = answer_time;
    }

    public Date getPreserve_time() {
        return preserve_time;
    }

    public void setPreserve_time(Date preserve_time) {
        this.preserve_time = preserve_time;
    }

    public String getFail_reason() {
        return fail_reason;
    }

    public void setFail_reason(String fail_reason) {
        this.fail_reason = fail_reason;
    }
}
