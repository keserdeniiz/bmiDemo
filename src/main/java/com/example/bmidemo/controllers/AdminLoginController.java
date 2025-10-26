package com.example.bmidemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {

        @GetMapping("/index/adminLogin")
        public String goadminLogin(){
            return "adminLogin";
        }
    }

