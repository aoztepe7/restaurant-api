package com.ztp.restaurantapi.domain.user;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> , JpaSpecificationExecutor<User> {
    User findByIdAndDeletedNotNull(UUID id);

    User findByMailAndDeletedNotNull(String mail);
}
