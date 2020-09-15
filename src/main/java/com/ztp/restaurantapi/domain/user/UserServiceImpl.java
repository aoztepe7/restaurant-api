package com.ztp.restaurantapi.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;


    @Override
    public User getByMail(String mail) {
        return Optional.ofNullable(userRepository.findByMailAndDeletedFalse(mail))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }
}
