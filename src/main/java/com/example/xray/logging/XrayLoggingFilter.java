package com.example.xray.logging;

import java.util.Objects;

import com.amazonaws.xray.AWSXRay;
import com.example.xray.constant.OrderFilter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(OrderFilter.XRAY_LOGGING_FILTER)
public class XrayLoggingFilter {
    @Around(value = "execution(* com.example.xray..*Controller.*(..))" +
            " || execution(* com.example.xray..*Service.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var globalRecorder = AWSXRay.getGlobalRecorder();
        var segmentName = this.getSegmentName(proceedingJoinPoint);

        var subsegment = globalRecorder.beginSubsegment(segmentName);
        Objects.requireNonNull(globalRecorder.getCurrentSegment())
                .addSubsegment(subsegment);

        var result = proceedingJoinPoint.proceed();

        globalRecorder.endSubsegment(subsegment);

        return result;
    }

    private String getSegmentName(ProceedingJoinPoint proceedingJoinPoint) {
        var classNameWithPackage = proceedingJoinPoint.getTarget().getClass().toString();
        var className = classNameWithPackage.substring(classNameWithPackage.lastIndexOf(".") + 1);
        var methodName = proceedingJoinPoint.getSignature().getName();
        return String.format("%s#%s", className, methodName);
    }
}
