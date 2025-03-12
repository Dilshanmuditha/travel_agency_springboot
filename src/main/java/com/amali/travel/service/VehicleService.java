package com.amali.travel.service;

import com.amali.travel.Security.JwtUtil;
import com.amali.travel.dto.ApiResponse;
import com.amali.travel.dto.CustomerDto;
import com.amali.travel.dto.VehicleDto;
import com.amali.travel.model.AdminUser;
import com.amali.travel.model.Customer;
import com.amali.travel.model.Vehicle;
import com.amali.travel.repository.CustomerRepository;
import com.amali.travel.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public VehicleService(VehicleRepository vehicleRepository, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.vehicleRepository = vehicleRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<ApiResponse<String>> signup(Vehicle vehicle) {
        if (vehicleRepository.findByEmail(vehicle.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict
                    .body(new ApiResponse<>(false, "Email already in use!", null));
        }

        vehicle.setPassword(passwordEncoder.encode(vehicle.getPassword()));
        vehicleRepository.save(vehicle);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 Created
                .body(new ApiResponse<>(true, "Signup successful!", null));
    }

    public ResponseEntity<ApiResponse<String>> signin(VehicleDto vehicleDto) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByEmail(vehicleDto.getEmail());

        if (vehicleOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401 Unauthorized
                    .body(new ApiResponse<>(false, "Invalid email or password", null));
        }

        Vehicle vehicle = vehicleOpt.get();
        if (!passwordEncoder.matches(vehicleDto.getPassword(), vehicle.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401 Unauthorized
                    .body(new ApiResponse<>(false, "Invalid email or password", null));
        }

        String token = jwtUtil.generateToken(vehicle.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK) // 200 OK
                .body(new ApiResponse<>(true, "Signin successful!", token));
    }

    public Optional<Vehicle> view(Integer id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> get() {
        return vehicleRepository.findAll();
    }
}
