package com.example.bmidemo.repository;

import com.example.bmidemo.model.BMIResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BMIResultRepository extends JpaRepository<BMIResult, Integer> {
    // Define any additional methods for specific queries or operations
}
