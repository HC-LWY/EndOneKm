package com.qf.endOneKm.aspect;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ExpressionParser expressionParser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around("@annotation(com.qf.endOneKm.annotation.LogonValidate)")
    public Object lockHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


            System.out.println("代理方法");


            Object proceed= proceedingJoinPoint.proceed();
            return proceed;

    }
}
