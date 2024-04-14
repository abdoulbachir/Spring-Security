package com.eazybytes.springsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class WelcomeController {
    @GetMapping("welcome")
    public String satWelcome(){
        return "Welcome to Spring Application Security";
    }
}
