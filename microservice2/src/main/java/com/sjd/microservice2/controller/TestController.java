package com.sjd.microservice2.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
// @XRayEnabled
public class TestController {

  @GetMapping
  public ResponseEntity<String> test() {

    String hostname;

    log.debug("Testing out Microservice2");

    try {

      hostname = InetAddress.getLocalHost().getHostName();

    } catch (UnknownHostException e) {

      log.error(e.getMessage());
      hostname = "NO HOST";
    }

    return new ResponseEntity<>("Hello from Microservice 2 (on " + hostname + ")", HttpStatus.OK);
  }

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    log.debug("Saying hello");

    return ResponseEntity.ok("Hello from microservice2");
  }
}
