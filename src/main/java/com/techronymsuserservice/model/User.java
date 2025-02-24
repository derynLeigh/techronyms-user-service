package com.techronymsuserservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant lastUpdated;

    public User(@NonNull String username, @NonNull String email, @NonNull String password, @NonNull UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
