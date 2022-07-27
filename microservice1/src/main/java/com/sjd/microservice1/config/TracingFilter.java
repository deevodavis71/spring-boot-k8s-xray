package com.sjd.microservice1.config;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.log4j.Log4JSegmentListener;
import com.amazonaws.xray.metrics.MetricsSegmentListener;
import com.amazonaws.xray.plugins.EC2Plugin;
import com.amazonaws.xray.plugins.EKSPlugin;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingFilter {

  @Value("${spring.application.name}")
  public String appName;

  @Bean
  public Filter xrayFilter() {

    AWSXRayRecorderBuilder builder =
        AWSXRayRecorderBuilder.standard()
            .withPlugin(new EC2Plugin())
            .withPlugin(new EKSPlugin())
            .withSegmentListener(new MetricsSegmentListener())
            .withSegmentListener(new Log4JSegmentListener(appName));

    AWSXRay.setGlobalRecorder(builder.build());

    return new AWSXRayServletFilter(appName);
  }
}
