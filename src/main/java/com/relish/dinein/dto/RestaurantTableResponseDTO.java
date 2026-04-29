package com.relish.dinein.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantTableResponseDTO {

    private Long id;
    private String tableNumber;
    private int capacity;
}
