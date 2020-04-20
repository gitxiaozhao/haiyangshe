package com.rainier.util;

/**
 * Created by Bobby on 2018/5/29.
 */
public class Result<T> {
    private  String code;
    private  String message;
    private T data;

    public static final Result success(){
        return new Result("200","Success");
    }
    public static final <T> Result success(T data){
        return new Result("200","Success",data);
    }

    public static final Result error(){
            return new Result("500","Error");
    }

    public static final Result error(String message){
            return new Result("500",message);
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, T data) {

        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {

    }

    public String  getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code == null? null : code.trim();
    }

    public String  getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message == null? null : message.trim();
    }

    public T getData() {
        return data;
    }
    public void setData( T data) {
        this.data = data;
    }

    public String isReturnResult() {

        return code;
    }
}
