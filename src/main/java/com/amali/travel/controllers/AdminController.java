package com.amali.travel.controllers;
import com.amali.travel.errorHandling.UniqueError;
import com.amali.travel.model.AdminUser;
import com.amali.travel.repository.AdminRepository;
import com.amali.travel.service.AdminService;
import com.amali.travel.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    AdminServiceImpl adminServiceImpl;
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/user")
    public String home() {
        return "hello world";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody AdminUser admin){
        try {
            AdminUser savedAdmin = adminServiceImpl.save(admin);
            return ResponseEntity.ok(savedAdmin);
        } catch (DataIntegrityViolationException ex) {
            String errorMessage = UniqueError.extractErrorMessage(ex);
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<?> getAdmin(@RequestParam Integer id) {
        try {
            Optional<AdminUser> viewAdmin = adminServiceImpl.view(id);
            if (viewAdmin.isPresent()) {
                return ResponseEntity.ok(viewAdmin.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found with ID: " + id);
            }
        } catch (DataIntegrityViolationException ex) {
            String errorMessage = UniqueError.extractErrorMessage(ex);
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
