package com.ztp.restaurantapi.domain.user;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> , JpaSpecificationExecutor<User> {
    User findByIdAndDeletedFalse(UUID id);

    User findByMailAndDeletedFalse(String mail);
}
