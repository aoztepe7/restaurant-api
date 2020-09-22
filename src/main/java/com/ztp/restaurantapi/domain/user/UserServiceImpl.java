package com.ztp.restaurantapi.domain.user;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantSpecification;
import com.ztp.restaurantapi.message.request.user.UserListRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private static final Integer PAGE_SIZE = 10;

    @Override
    public User getById(UUID id) {
        return Optional.ofNullable(userRepository.findByIdAndDeletedNotNull(id))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getByMail(String mail) {
        return Optional.ofNullable(userRepository.findByMailAndDeletedNotNull(mail))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        getById(user.getId());
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        getById(user.getId());
        user.setDeleted(null);
        userRepository.save(user);
    }

    @Override
    public User getUserByAuth(Authentication authentication) {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Page<User> search(UserListRequest command) {
        int pageNumber = command.getPage() == null || command.getPage() <= 1 ? 0 : command.getPage() -1;
        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE, Sort.by(Sort.Order.asc("firstName")));
        Specification<User> specification = new UserSpecification(command);
        return userRepository.findAll(specification,pageable);
    }
}
