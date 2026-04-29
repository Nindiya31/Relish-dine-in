package com.relish.dinein.controller;

import com.relish.dinein.dto.RestaurantTableRequestDTO;
import com.relish.dinein.dto.RestaurantTableResponseDTO;
import com.relish.dinein.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService service;

    @PostMapping("/createTable")
    public RestaurantTableResponseDTO create(@RequestBody RestaurantTableRequestDTO request) {
        return service.createTable(request);
    }

    @GetMapping("/getAllTables")
    public List<RestaurantTableResponseDTO> getAll() {
        return service.getAllTables();
    }

    @DeleteMapping("deleteTable/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteTable(id);
        return "Table deleted successfully";
    }
}