package com.sjd.microservice1.config;

import com.amazonaws.xray.spring.aop.BaseAbstractXRayInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class XRayInspector extends BaseAbstractXRayInterceptor {
  @Override
  @Pointcut("within(com.sjd..*) && bean(*Controller)")
  public void xrayEnabledClasses() {
    // Pointcut
  }
}
