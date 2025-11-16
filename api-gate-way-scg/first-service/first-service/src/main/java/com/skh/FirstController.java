package com.skh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/fs")
public class FirstController {
    private static final Logger log = LoggerFactory.getLogger(FirstController.class);

    @GetMapping("/message")
    public String test() {
        String message = String.format("%s method called in %s!!!, time %s",Thread.currentThread().getStackTrace()[1].getMethodName(), this.getClass().getName(), LocalDateTime.now());
        log.info(message);
        return message;
    }
}