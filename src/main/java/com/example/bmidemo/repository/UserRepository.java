package com.example.bmidemo.repository;

import com.example.bmidemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
        List<User> findByLastName(String lastName);
}
