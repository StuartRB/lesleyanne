package com.example.lesleyanne.repository;

import com.example.lesleyanne.model.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFormRepository extends JpaRepository<UserForm, Long> {
    List<UserForm> findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address);
}