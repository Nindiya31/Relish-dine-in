package com.relish.dinein.dto;


import lombok.Data;

@Data
public class RestaurantTableRequestDTO {

    private String tableNumber;
    private int capacity;
}
