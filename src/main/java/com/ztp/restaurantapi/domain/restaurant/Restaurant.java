package com.ztp.restaurantapi.domain.restaurant;

import com.ztp.restaurantapi.domain.BaseEntity;
import com.ztp.restaurantapi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "rate")
    private Double rate;
}
