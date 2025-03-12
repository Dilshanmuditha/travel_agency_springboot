package com.amali.travel.service;

import com.amali.travel.model.AdminUser;
import com.amali.travel.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    public AdminUser save(AdminUser admin){return adminRepository.save(admin);}

    public Optional<AdminUser> view(Integer id) {
        return adminRepository.findById(id);
    }
}
