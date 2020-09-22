package com.ztp.restaurantapi.domain.restaurant;

import com.ztp.restaurantapi.message.request.restaurant.RestaurantListRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    private static final Integer PAGE_SIZE = 10;

    @Override
    public Restaurant getById(UUID id) {
        return Optional.ofNullable(restaurantRepository.findByIdAndDeletedNotNull(id))
                .orElseThrow(RestaurantNotFoundException::new);
    }

    @Override
    public void create(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(Restaurant restaurant) {
        getById(restaurant.getId());
        restaurant.setDeleted(null);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        getById(restaurant.getId());
        restaurantRepository.save(restaurant);
    }

    @Override
    public Page<Restaurant> search(RestaurantListRequest command) {
        int pageNumber = command.getPage() == null || command.getPage() <= 1 ? 0 : command.getPage() -1;
        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(Sort.Order.desc("rate")));
        Specification<Restaurant> specification = new RestaurantSpecification(command);
        return restaurantRepository.findAll(specification,pageable);
    }
}
