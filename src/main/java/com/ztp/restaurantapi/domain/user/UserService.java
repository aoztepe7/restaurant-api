package com.ztp.restaurantapi.domain.user;

public interface UserService {
    User getByMail(String mail);

    void create(User user);
}
