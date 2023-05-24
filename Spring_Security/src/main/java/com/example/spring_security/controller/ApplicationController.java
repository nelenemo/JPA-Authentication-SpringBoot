package com.example.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api")
public class ApplicationController {
    @GetMapping("/get")
    public String process()
    {
        return "passed the spring security through DB";
    }
}
//get data from db and apply the spring security