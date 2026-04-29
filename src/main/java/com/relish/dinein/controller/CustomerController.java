package com.relish.dinein.controller;

import com.relish.dinein.dto.CustomerRequestDTO;
import com.relish.dinein.dto.CustomerResponseDTO;
import com.relish.dinein.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/create")
    public CustomerResponseDTO createOrGet(@RequestBody CustomerRequestDTO request) {
        return service.createOrGetCustomer(request);
    }
}