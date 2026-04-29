package com.relish.dinein.service;

import com.relish.dinein.dto.RestaurantTableRequestDTO;
import com.relish.dinein.dto.RestaurantTableResponseDTO;
import com.relish.dinein.entity.RestaurantTable;
import com.relish.dinein.exception.ResourceNotFoundException;
import com.relish.dinein.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository repository;

    public RestaurantTableResponseDTO createTable(RestaurantTableRequestDTO request) {
        try {
            RestaurantTable table = RestaurantTable.builder()
                    .tableNumber(request.getTableNumber())
                    .capacity(request.getCapacity())
                    .build();

            RestaurantTable saved = repository.save(table);

            return RestaurantTableResponseDTO.builder()
                    .id(saved.getId())
                    .tableNumber(saved.getTableNumber())
                    .capacity(saved.getCapacity())
                    .build();

        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to create table. Please try again.");
        }
    }

    public List<RestaurantTableResponseDTO> getAllTables() {
        try {
            return repository.findAll().stream().map(table ->
                    RestaurantTableResponseDTO.builder()
                            .id(table.getId())
                            .tableNumber(table.getTableNumber())
                            .capacity(table.getCapacity())
                            .build()
            ).toList();

        } catch (Exception ex) {
            throw new RuntimeException("Failed to fetch tables.");
        }
    }

    public void deleteTable(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("Table not found with id: " + id);
            }
            repository.deleteById(id);

        } catch (ResourceNotFoundException ex) {
            throw ex; // rethrow (important)

        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to delete table.");
        }
    }
}