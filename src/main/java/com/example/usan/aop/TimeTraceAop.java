package com.example.usan.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

//    @Around("execution(* com.example.usan..*(..))")
//    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
//        long start = System.currentTimeMillis();
//        System.out.println("START: " + joinPoint.toShortString());
//        try {
//            return joinPoint.proceed();
//        }
//        finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("END: " + joinPoint.toShortString()+ "  "+ timeMs+ "ms");
//        }
//
//    }
}
