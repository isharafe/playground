package com.my.product.catalog.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ProductControllerRequestLogger {

	@Around("execution(* com.my.product.catalog.controller.ProductController.*(..)))")
    public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        long t1 = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.nanoTime();

        //Log method execution time
        log.info(String.format("ProductController: %s.%s, reqIn=%d, reqOver=%d, reqTime=%d",
        		methodSignature.getDeclaringType().getSimpleName(), methodSignature.getName(), t1, t2, t2-t1));
        return result;
    }

	@Around("execution(* com.my.product.client.InventoryClient.getProductInventory(..)))")
    public Object inventoryClientExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        long t1 = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.nanoTime();

        //Log method execution time
        log.info(String.format("InventoryClient request: %s.%s, reqIn=%d, reqOver=%d, reqTime=%d",
        		methodSignature.getDeclaringType().getSimpleName(), methodSignature.getName(), t1, t2, t2-t1));
        return result;
    }
}
