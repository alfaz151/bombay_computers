package com.bombaycomputers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@RestController
public class HomeController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/")
    public String rootRoute() {
        log.info("Service is running on port: {}", port);
        return "Service is running on port: " + port;
    }

}