package com.ztp.restaurantapi.domain.user;

import com.ztp.restaurantapi.message.request.user.UserListRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserSpecification implements Specification<User> {

    private final UserListRequest userListRequest;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(userListRequest.getFirstName())){
            predicates.add(cb.like(cb.upper(root.get("firstName")),"%"+userListRequest.getFirstName().toUpperCase()+"%"));
        }

        if(!StringUtils.isEmpty(userListRequest.getLastName())){
            predicates.add(cb.like(cb.upper(root.get("lastName")),"%"+userListRequest.getLastName().toUpperCase()+"%"));
        }

        if(!StringUtils.isEmpty(userListRequest.getMail())){
            predicates.add(cb.like(cb.upper(root.get("mail")),"%"+userListRequest.getMail().toUpperCase()+"%"));
        }

        if(userListRequest.getRole() != null){
            predicates.add(cb.and(cb.equal(root.get("role"),userListRequest.getRole())));
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
