package com.example.bmidemo.controllers;

import com.example.bmidemo.model.BMIResult;
import com.example.bmidemo.model.User;
import com.example.bmidemo.service.BMIResultService;
import com.example.bmidemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;
    private final BMIResultService bmiResultService; // Add this line

    @Autowired
    public UserController(UserService userService, BMIResultService bmiResultService) {
        this.userService = userService;
        this.bmiResultService = bmiResultService; // Add this line
    }

    @PostMapping("/saveUser")
    public @ResponseBody
    Map<String, String> saveUser(@RequestBody User user) {
        // Calculate BMI
        double bmi = user.getWeight() / (user.getHeight() * user.getHeight());

        // Set the BMI classification and index range
        String classification;
        String indexRange;
        if (bmi < 16) {
            classification = "Severe Thinness";
            indexRange = "< 16";
        } else if (bmi < 17) {
            classification = "Moderate Thinness";
            indexRange = "16 - 17";
        } else if (bmi < 18.5) {
            classification = "Mild Thinness";
            indexRange = "17 - 18.5";
        } else if (bmi < 25) {
            classification = "Normal";
            indexRange = "18.5 - 25";
        } else if (bmi < 30) {
            classification = "Overweight";
            indexRange = "25 - 30";
        } else if (bmi < 35) {
            classification = "Obese Class I";
            indexRange = "30 - 35";
        } else if (bmi < 40) {
            classification = "Obese Class II";
            indexRange = "35 - 40";
        } else {
            classification = "Obese Class III";
            indexRange = "> 40";
        }

        // Save user to the database
        User savedUser = userService.saveUser(user);

        // Create the BMI_Result entry
        BMIResult bmiResult = new BMIResult();
        bmiResult.setUserId(savedUser.getId());
        bmiResult.setClassification(classification);
        bmiResult.setIndexRange(indexRange);
        bmiResult.setCreatedAt(LocalDateTime.now()); // Set the current timestamp

        // Save the BMI_Result entry to the database
        bmiResultService.saveBMIResult(bmiResult);

        // Create a map to hold the BMI information
        Map<String, String> bmiInfo = new HashMap<>();
        bmiInfo.put("classification", classification);
        bmiInfo.put("indexRange", indexRange);

        return bmiInfo;
    }
}