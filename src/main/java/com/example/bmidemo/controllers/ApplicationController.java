package com.example.bmidemo.controllers;

import com.example.bmidemo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @GetMapping("/index")
    public String goHome() {
        return "index";
    }

    @PostMapping("/connection")
    @ResponseBody
    public ResponseEntity<?> connection() {
        try {
            // veritabanı bağlantısı yap
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user){
        System.out.println(user.toString());
        return "index";
    }


}