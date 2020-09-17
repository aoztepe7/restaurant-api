package com.ztp.restaurantapi.domain.review;

import com.ztp.restaurantapi.domain.restaurant.*;
import com.ztp.restaurantapi.message.request.review.ReviewListRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private static final Integer PAGE_SIZE = 10;


    @Override
    public Review getById(UUID id) {
        return Optional.ofNullable(reviewRepository.findByIdAndDeletedFalse(id))
                .orElseThrow(ReviewNotFoundException::new);
    }

    @Override
    public void create(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void delete(Review review) {
        getById(review.getId());
        review.setDeleted(null);
        reviewRepository.save(review);
    }

    @Override
    public void update(Review review) {
        getById(review.getId());
        reviewRepository.save(review);
    }

    @Override
    public Page<Review> search(ReviewListRequest command) {
        int pageNumber = command.getPage() == null || command.getPage() <= 1 ? 0 : command.getPage() -1;
        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(Sort.Order.asc("visitDate")));
        Specification<Review> specification = new ReviewSpecification(command);
        return reviewRepository.findAll(specification,pageable);
    }

    @Override
    public List<Review> getAllReviewsByRestaurant(UUID restaurantId) {
        return reviewRepository.findAllByRestaurantIdAndDeletedFalse(restaurantId);
    }
}
