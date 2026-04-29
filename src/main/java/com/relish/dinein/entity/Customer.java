package com.relish.dinein.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String mobile;

    private LocalDateTime createdAt;

    @PrePersist // auto timestamp 
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}