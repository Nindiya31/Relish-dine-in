package com.relish.dinein.service;

import com.relish.dinein.dto.RestaurantTableRequestDTO;
import com.relish.dinein.dto.RestaurantTableResponseDTO;
import com.relish.dinein.entity.RestaurantTable;
import com.relish.dinein.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository repository;

    public RestaurantTableResponseDTO createTable(RestaurantTableRequestDTO request) {

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
    }

    public List<RestaurantTableResponseDTO> getAllTables() {

        return repository.findAll().stream().map(table ->
                RestaurantTableResponseDTO.builder()
                        .id(table.getId())
                        .tableNumber(table.getTableNumber())
                        .capacity(table.getCapacity())
                        .build()
        ).toList();
    }

    public void deleteTable(Long id) {
        repository.deleteById(id);
    }
}