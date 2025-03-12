package com.amali.travel.controllers;

import com.amali.travel.dto.ApiResponse;
import com.amali.travel.dto.CustomerDto;
import com.amali.travel.model.Customer;
import com.amali.travel.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@CrossOrigin(origins = "*")
public class AuthController {
    private final CustomerService customerService;

    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody Customer customer) {
        return customerService.signup(customer);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<String>> signin(@RequestBody CustomerDto customerDTO) {
        return customerService.signin(customerDTO);
    }
}
