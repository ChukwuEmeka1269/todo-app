package com.rexcoin.springboottodoapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

//    @CreatedDate
    @Column(updatable = false)
     LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
     String createdBy;

//    @LastModifiedDate
//    @Column(insertable = false)
     LocalDateTime updateAt;

    @LastModifiedBy
    @Column(insertable = false)
     String updateBy;
}
