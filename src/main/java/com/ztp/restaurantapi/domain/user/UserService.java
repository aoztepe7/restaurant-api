package com.ztp.restaurantapi.domain.user;

import com.ztp.restaurantapi.message.request.user.UserListRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface UserService {
    User getById(UUID id);

    User getByMail(String mail);

    void create(User user);

    void update(User user);

    void delete(User user);

    User getUserByAuth(Authentication authentication);

    Page<User> search(UserListRequest command);
}
