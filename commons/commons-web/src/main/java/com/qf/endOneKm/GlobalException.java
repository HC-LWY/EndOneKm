package com.qf.endOneKm;

import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.exception.JSONException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */



//@RestControllerAdvice
public class GlobalException implements ResponseBodyAdvice<R> {

    /**
     * 所有异常捕捉
     * @param t 最顶级异常类
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public R exceptionHandler(Throwable t){
        t.printStackTrace();
        return R.error().put("msg","该异常无法捕获");
    }

    @ExceptionHandler(BindException.class)
    public R bindException(BindException e){
       List<String> errors=e.getBindingResult().getAllErrors().stream()
               .map(s->s.getDefaultMessage())
               .collect(Collectors.toList());
        return R.error().put("errors",errors);
    }

    /**
     * 异步业务异常
     * @param e 自定义异常类
     * @return
     */
    @ExceptionHandler(JSONException.class)
    public R jsonException(JSONException e){
        return new R().put("code",e.code).put("msg",e.msg);
    }

    /**
     * 同步业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public R businessException(BusinessException e){
        return new R().put("code",e.code).put("msg",e.msg);
    }

    /**
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getMethod().getReturnType() == R.class;
    }

    /**
     *
     * @param r
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public R beforeBodyWrite(R r, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(!r.get(ExceptionConstant.CODE).equals(ExceptionConstant.OK)){
            serverHttpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return r;
    }
}
