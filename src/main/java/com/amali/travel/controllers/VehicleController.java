package com.amali.travel.controllers;

import com.amali.travel.dto.ApiResponse;
import com.amali.travel.dto.CustomerDto;
import com.amali.travel.dto.VehicleDto;
import com.amali.travel.errorHandling.UniqueError;
import com.amali.travel.model.AdminUser;
import com.amali.travel.model.Customer;
import com.amali.travel.model.Vehicle;
import com.amali.travel.repository.AdminRepository;
import com.amali.travel.service.AdminService;
import com.amali.travel.service.AdminServiceImpl;
import com.amali.travel.service.CustomerService;
import com.amali.travel.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody Vehicle vehicle) {
        return vehicleService.signup(vehicle);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<String>> signin(@RequestBody VehicleDto vehicleDto) {
        return vehicleService.signin(vehicleDto);
    }

    @GetMapping("/vehicle")
    public ResponseEntity<?> getVehicle(@RequestParam Integer id) {
        try {
            Optional<Vehicle> viewVehicle = vehicleService.view(id);
            if (viewVehicle.isPresent()) {
                return ResponseEntity.ok(viewVehicle.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found with ID: " + id);
            }
        } catch (DataIntegrityViolationException ex) {
            String errorMessage = UniqueError.extractErrorMessage(ex);
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> getVehiclesList() {
        try {
            List<Vehicle> viewVehicle = vehicleService.get();
            if (viewVehicle.isEmpty()) {
                return ResponseEntity.ok(viewVehicle);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicles not found ");
            }
        } catch (DataIntegrityViolationException ex) {
            String errorMessage = UniqueError.extractErrorMessage(ex);
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
