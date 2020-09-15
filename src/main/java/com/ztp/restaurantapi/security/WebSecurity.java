package com.ztp.restaurantapi.security;

import com.google.gson.Gson;
import com.ztp.restaurantapi.config.JwtConfig;
import com.ztp.restaurantapi.domain.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtConfig jwtConfig;
    private final Gson gson;
    private final UserService userService;

    public WebSecurity(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, JwtConfig jwtConfig, Gson gson, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtConfig = jwtConfig;
        this.gson = gson;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure().and()
                .cors().and().csrf().disable()
                .headers().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/**").authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtConfig, gson, userService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtConfig, gson, userService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
