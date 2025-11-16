package com.skh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ss")
public class SecondController {
    private static final Logger log = LoggerFactory.getLogger(SecondController.class);

    @Autowired
    private Environment environment;

    @GetMapping("/message")
    public String test() {
        String message = String.format("%s method called in %s!!!, Running on PORT: %s",Thread.currentThread().getStackTrace()[1].getMethodName(), this.getClass().getName(), environment.getProperty("server.port"));
        log.info(message);
        return message;
    }
}