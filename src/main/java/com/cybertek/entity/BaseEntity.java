package com.cybertek.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime insertDateTime;
    private Long insertUserID;
    private LocalDateTime lastUpdateDateTime;
    private Long lastUpdateUserID;

    private Boolean isDeleted = false;

    @PrePersist
    private void onPrePersist() {
        this.insertDateTime = LocalDateTime.now();
        this.lastUpdateDateTime = LocalDateTime.now();
        // will be done with security
        this.insertUserID = 1L;
        this.lastUpdateUserID = 1L;
    }

    @PreUpdate
    private void onPreUpdate() {
        this.lastUpdateDateTime = LocalDateTime.now();
        this.lastUpdateUserID = 1L;
    }

}
