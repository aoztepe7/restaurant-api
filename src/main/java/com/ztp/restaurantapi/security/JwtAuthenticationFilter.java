package com.ztp.restaurantapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ztp.restaurantapi.config.JwtConfig;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.exception.UnauthorizedException;
import com.ztp.restaurantapi.message.response.LoginResponse;
import com.ztp.restaurantapi.message.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final Gson gson;
    private final UserService userService;

    private static final String USER = "user";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, Gson gson, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.gson = gson;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getMail(),
                            credentials.getPassword(),
                            new ArrayList<>())
            );
        } catch (AuthenticationException | IOException e) {
            //
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Claims claims = Jwts.claims().setSubject(USER);
        claims.put("role", ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getAuthorities()
                .stream().findFirst().get().toString());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();


        LoginResponse loginResponse = LoginResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .token(token)
                .build();


        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().

                write(gson.toJson(loginResponse));
        response.getWriter().

                flush();
        response.getWriter().

                close();

    }
}
