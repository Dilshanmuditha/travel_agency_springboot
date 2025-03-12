package com.amali.travel.repository;

import com.amali.travel.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<AdminUser, Integer> {
    AdminUser findById(int id);
    AdminUser findByEmailIgnoreCase(String email);
}
