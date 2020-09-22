package com.ztp.restaurantapi;

import com.ztp.restaurantapi.domain.user.Role;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestaurantApiApplication {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        SpringApplication.run(RestaurantApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200","http://localhost:4300","http://localhost:4400");
            }
        };
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            if (userRepository.findByMailAndDeletedNotNull("san-tsg@mail.com") == null) {
                User user = new User();
                user.setFirstName("San");
                user.setLastName("TSG");
                user.setMail("san-tsg@mail.com");
                user.setPassword(encoder.encode("12345678"));
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            }

        };
    }


}
