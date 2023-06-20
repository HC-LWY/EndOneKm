package com.qf.endOneKm.exception;

// 错误视图
public class ErrorPageException extends BusinessException {
    public ErrorPageException() {
    }

    public ErrorPageException(Integer code, String msg) {
        super(code, msg);
    }
}
