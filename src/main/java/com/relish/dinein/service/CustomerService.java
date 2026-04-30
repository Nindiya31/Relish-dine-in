package com.relish.dinein.service;

import com.relish.dinein.dto.CustomerRequestDTO;
import com.relish.dinein.dto.CustomerResponseDTO;
import com.relish.dinein.entity.Customer;
import com.relish.dinein.exception.BadRequestException;
import com.relish.dinein.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {

        if (request.getMobile() == null || request.getMobile().isBlank()) {
            throw new BadRequestException("Mobile number is required");
        }

        if (repository.findByMobile(request.getMobile()).isPresent()) {
            throw new BadRequestException("Customer already exists");
        }

        Customer customer = repository.save(
                Customer.builder()
                        .name(request.getName())
                        .mobile(request.getMobile())
                        .build()
        );

        return mapToResponse(customer);
    }


    public List<CustomerResponseDTO> getAllCustomers() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CustomerResponseDTO getCustomerByMobile(String mobile) {

        Customer customer = repository.findByMobile(mobile)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return mapToResponse(customer);
    }

    private CustomerResponseDTO mapToResponse(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .mobile(customer.getMobile())
                .build();
    }
}