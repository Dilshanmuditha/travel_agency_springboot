package com.amali.travel.service;

import com.amali.travel.Security.JwtUtil;
import com.amali.travel.dto.ApiResponse;
import com.amali.travel.dto.CustomerDto;
import com.amali.travel.model.Customer;
import com.amali.travel.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<ApiResponse<String>> signup(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict
                    .body(new ApiResponse<>(false, "Email already in use!", null));
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 Created
                .body(new ApiResponse<>(true, "Signup successful!", null));
    }

    public ResponseEntity<ApiResponse<String>> signin(CustomerDto customerDTO) {
        Optional<Customer> customerOpt = customerRepository.findByEmail(customerDTO.getEmail());

        if (customerOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401 Unauthorized
                    .body(new ApiResponse<>(false, "Invalid email or password", null));
        }

        Customer customer = customerOpt.get();
        if (!passwordEncoder.matches(customerDTO.getPassword(), customer.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401 Unauthorized
                    .body(new ApiResponse<>(false, "Invalid email or password", null));
        }

        String token = jwtUtil.generateToken(customer.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK) // 200 OK
                .body(new ApiResponse<>(true, "Signin successful!", token));
    }

}
