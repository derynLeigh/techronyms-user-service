package com.techronymsuserservice.model;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Cacheable(false)

public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String fullName;
//    private String email;
//
//    @CreationTimestamp
//    private Instant created;
//
//    @UpdateTimestamp
//    private Instant lastUpdated;
//
//    public User(String fullName, String email) {
//        this.fullName = fullName;
//        this.email = email;
//    }
}
