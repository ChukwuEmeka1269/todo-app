package com.rexcoin.springboottodoapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
     LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
     String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
     LocalDateTime updateAt;

    @LastModifiedBy
    @Column(insertable = false)
     String updateBy;


    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (createdAt == null && createdBy == null) {
            createdAt = LocalDateTime.now();
            createdBy = "ADMIN";
        }
        updateAt = LocalDateTime.now();
    }
}
