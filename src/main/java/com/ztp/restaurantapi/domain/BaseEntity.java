package com.ztp.restaurantapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @Column(name = "uuid",nullable = false)
    private UUID id;

    @Column()
    private Boolean deleted = false;

    @PrePersist
    private void onPersist() {
        id = UUID.randomUUID();
    }



}
