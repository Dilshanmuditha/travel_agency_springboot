package com.amali.travel.repository;

import com.amali.travel.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminUser, Integer> {
    AdminUser findByEmail(String email);
}
