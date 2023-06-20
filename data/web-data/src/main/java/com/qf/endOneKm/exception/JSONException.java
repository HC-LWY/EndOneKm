package com.qf.endOneKm.exception;


// 响应JSON就手动抛出这个异常
public class JSONException extends BusinessException {

    public JSONException() {
    }

    public JSONException(Integer code, String msg) {
        super(code,msg);
    }
}
