package com.example.springdatajpa.repository;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String name;
    @Column(name = "`column`")
    private Long column;
    @Column(name = "`row`")
    private Long row;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;

    @PrePersist
    public void onPersist(){
        timeCreated = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        timeUpdated = LocalDateTime.now();
    }

}
