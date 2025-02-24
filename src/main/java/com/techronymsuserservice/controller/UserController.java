package com.techronymsuserservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/public/contact")
    public String sayHi() {
        return "Contact";
    }

    @GetMapping("/admin")
    public String restricted() {
        return "Admin only";
    }

}
