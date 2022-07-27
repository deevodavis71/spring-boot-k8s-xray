package com.sjd.microservice2.config;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.log4j.Log4JSegmentListener;
import com.amazonaws.xray.metrics.MetricsSegmentListener;
import com.amazonaws.xray.plugins.EC2Plugin;
import com.amazonaws.xray.plugins.EKSPlugin;
import javax.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingFilter {

  static {
    AWSXRayRecorderBuilder builder =
        AWSXRayRecorderBuilder.standard()
            .withPlugin(new EC2Plugin())
            .withPlugin(new EKSPlugin())
            .withSegmentListener(new MetricsSegmentListener())
            .withSegmentListener(new Log4JSegmentListener("microservice2"));

    AWSXRay.setGlobalRecorder(builder.build());
  }

  @Bean
  public Filter xrayFilter() {
    return new AWSXRayServletFilter("microservice2");
  }
}
