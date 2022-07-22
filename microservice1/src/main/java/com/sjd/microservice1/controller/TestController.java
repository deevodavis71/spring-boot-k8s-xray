package com.sjd.microservice1.controller;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/test")
@XRayEnabled
public class TestController {

  @Value("${sjd.greeting:Hey}")
  private String greeting;

  @Value("${sjd.env-name:no-value}")
  private String envName;

  @Value("${service-endpoints.microservice2}")
  private String microservice2Endpoint;

  @Autowired private RestTemplate restTemplate;

  @GetMapping
  public ResponseEntity<String> test() {

    String messageFromMicroservice2 = null;

    log.debug("Testing out Microservice1");

    try {

      log.debug("Connecting to : " + messageFromMicroservice2);

      messageFromMicroservice2 =
          restTemplate.getForObject(microservice2Endpoint + "/microservice2/test", String.class);

    } catch (RestClientException e) {

      log.error(e.getMessage());
      messageFromMicroservice2 = "FAILED!!";
    }

    return new ResponseEntity<>(
        greeting
            + " : "
            + envName
            + " (ms2 = "
            + microservice2Endpoint
            + ", "
            + messageFromMicroservice2
            + ")",
        HttpStatus.OK);
  }

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    log.debug("Saying hello");

    return ResponseEntity.ok("Hello from microservice1");
  }
}
