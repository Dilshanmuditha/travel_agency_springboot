package com.amali.travel.controllers;
import com.amali.travel.errorHandling.UniqueError;
import com.amali.travel.model.AdminUser;
import com.amali.travel.service.AdminService;
import com.amali.travel.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    AdminServiceImpl adminServiceImpl;
    @Autowired
    AdminService adminService;

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
}
