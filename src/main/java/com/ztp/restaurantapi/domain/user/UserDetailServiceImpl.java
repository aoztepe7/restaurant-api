package com.ztp.restaurantapi.domain.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private static final String ROLE_PREFIX = "ROLE_";

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMailAndDeletedNotNull(mail);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException(mail);
        }
        return new org.springframework.security.core.userdetails.User(user.getMail(),user.getPassword(),getAuthority(user));
    }

    private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(String.format("%s%s", ROLE_PREFIX, user.getRole())));
        return authorities;
    }

}
