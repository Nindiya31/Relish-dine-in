package com.relish.dinein.service;

import com.relish.dinein.dto.CustomerRequestDTO;
import com.relish.dinein.dto.CustomerResponseDTO;
import com.relish.dinein.entity.Customer;
import com.relish.dinein.exception.BadRequestException;
import com.relish.dinein.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponseDTO createOrGetCustomer(CustomerRequestDTO request) {

        try {
            // 🔹 Validation
            if (request.getMobile() == null || request.getMobile().isBlank()) {
                throw new BadRequestException("Mobile number is required");
            }

            Customer customer = repository.findByMobile(request.getMobile())
                    .orElseGet(() -> {
                        try {
                            return repository.save(
                                    Customer.builder()
                                            .name(request.getName())
                                            .mobile(request.getMobile())
                                            .build()
                            );
                        } catch (DataAccessException ex) {
                            throw new RuntimeException("Failed to create customer");
                        }
                    });

            return CustomerResponseDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .mobile(customer.getMobile())
                    .build();

        } catch (BadRequestException ex) {
            throw ex; // rethrow

        } catch (DataAccessException ex) {
            throw new RuntimeException("Database error while fetching customer");

        } catch (Exception ex) {
            throw new RuntimeException("Something went wrong while processing customer");
        }
    }
}