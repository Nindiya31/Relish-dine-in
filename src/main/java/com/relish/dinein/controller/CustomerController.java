package com.relish.dinein.controller;

import com.relish.dinein.dto.CustomerRequestDTO;
import com.relish.dinein.dto.CustomerResponseDTO;
import com.relish.dinein.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    // CREATE
    @PostMapping
    public CustomerResponseDTO create(@RequestBody CustomerRequestDTO request) {
        return service.createCustomer(request);
    }

    // GET ALL
    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        return service.getAllCustomers();
    }

    // GET BY MOBILE
    @GetMapping("/{mobile}")
    public CustomerResponseDTO getByMobile(@PathVariable String mobile) {
        return service.getCustomerByMobile(mobile);
    }
}