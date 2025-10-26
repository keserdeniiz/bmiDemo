package com.example.bmidemo.service;

import com.example.bmidemo.model.BMIResult;
import com.example.bmidemo.repository.BMIResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BMIResultService {
    private final BMIResultRepository bmiResultRepository;

    @Autowired
    public BMIResultService(BMIResultRepository bmiResultRepository) {
        this.bmiResultRepository = bmiResultRepository;
    }

    public BMIResult saveBMIResult(BMIResult bmiResult) {
        return bmiResultRepository.save(bmiResult);
    }
}
