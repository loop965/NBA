package com.yf.producer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: yf
 * @date: 2020/09/03  13:49
 * @desc:
 */

@Component
@Aspect
@Slf4j
public class EnableCountTimeAspect {

    @Pointcut("@annotation(com.yf.producer.aspect.Time)")
    public void consume(){

    }


    @Around("consume()")
    public Object handlingTimeAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            Object proceed = pjp.proceed();
            System.out.println(proceed);
            System.out.println("方法执行时间：" + (System.currentTimeMillis() - startTime));
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;



    }

}
