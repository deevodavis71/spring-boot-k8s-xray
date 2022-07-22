package com.sjd.microservice1.utils;

import com.amazonaws.xray.proxies.apache.http.HttpClientBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtils {

  @Bean
  public RestTemplate xrayEnabledRestTemplate(RestTemplateBuilder restTemplateBuilder) {

    CloseableHttpClient client = HttpClientBuilder.create().build();

    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
        new HttpComponentsClientHttpRequestFactory(client);

    return restTemplateBuilder.requestFactory(() -> clientHttpRequestFactory).build();
  }
}
