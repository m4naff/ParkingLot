package com.parkinglot.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDomainModel {
    @Column(name = "CREATED_USER")
    protected String createdUser;
    @Column(name = "CREATED_AT")
    protected LocalDateTime createdAt;
    @Column(name = "UPDATED_USER")
    protected String updatedUser;
    @Column(name = "UPDATED_AT")
    protected LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdUser = getUserNameFromAuthentication();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedUser = getUserNameFromAuthentication();
        this.updatedAt = LocalDateTime.now();
    }

    private String getUserNameFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getUsername();
        }
    }
}
