package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    // injection
    // 1. annotation -> @Autowired
    /// 클래스 이름 정확히 쓰거나, 빈을 설정해서 충돌을 피할수 있다.
    // 2. constructor
    // 3. setter method
    ///alt + insert

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return "Good Morning";
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(
            @RequestHeader(name="Accept-Language", required = false) Locale locale) {
        // %s님, 안녕하세요,
        return messageSource.getMessage(
                "greeting.message", null, locale);
    }

}
