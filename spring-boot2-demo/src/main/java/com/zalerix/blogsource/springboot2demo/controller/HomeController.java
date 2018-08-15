package com.zalerix.blogsource.springboot2demo.controller;


import com.zalerix.blogsource.springboot2demo.configuration.CustomConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("home")
@RestController
public class HomeController {

    @Autowired
    private CustomConfiguration customConfiguration;

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @GetMapping(path = "/custom")
    public String getCustomConfiguration() {
        return customConfiguration.getProperty();
    }

}
