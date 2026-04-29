package com.relish.dinein.service;

import com.relish.dinein.dto.CustomerRequestDTO;
import com.relish.dinein.dto.CustomerResponseDTO;
import com.relish.dinein.entity.Customer;
import com.relish.dinein.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponseDTO createOrGetCustomer(CustomerRequestDTO request) {

        Customer customer = repository.findByMobile(request.getMobile())
                .orElseGet(() -> repository.save(
                        Customer.builder()
                                .name(request.getName())
                                .mobile(request.getMobile())
                                .build()
                ));

        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .mobile(customer.getMobile())
                .build();
    }
}