package com.rainier.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Problem {
    private Integer id;
    private Integer user_id;
    private String problem;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date problem_time;
    private Integer problem_state;
    private String problem_img;
    private Integer click;
    private Integer answer;

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

    public Date getProblem_time() {
        return problem_time;
    }

    public void setProblem_time(Date problem_time) {
        this.problem_time = problem_time;
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
}
