package com.qf.endOneKm.exception;




public class BusinessException extends RuntimeException {

    public Integer code;
    public String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(Integer code, String msg) {

        this.code = code;
        this.msg = msg;

    }
}