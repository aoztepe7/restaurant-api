package com.ztp.restaurantapi.security;

import com.google.gson.Gson;
import com.ztp.restaurantapi.config.JwtConfig;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.message.response.LoginResponse;
import com.ztp.restaurantapi.message.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final JwtConfig jwtConfig;
    private final Gson gson;
    private final UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, Gson gson,UserService userService) {
        super(authenticationManager);
        this.jwtConfig = jwtConfig;
        this.gson = gson;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(jwtConfig.getHeaderString());
        if(header == null){
            chain.doFilter(request,response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request,response);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String token = request.getHeader(jwtConfig.getHeaderString());
        if (token != null) {
            Claims claims = null;

            try {
                claims = Jwts.parser()
                        .setSigningKey(jwtConfig.getSecret().getBytes())
                        .parseClaimsJws(token.replace(jwtConfig.getTokenPrefix(), "").trim())
                        .getBody();
            } catch (Exception e) {
                LoginResponse loginResponse = LoginResponse.builder()
                        .responseCode(ResponseCode.ACCESS_DENIED)
                        .build();

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(gson.toJson(loginResponse));
                response.getWriter().flush();
                response.getWriter().close();
            }

            String role = (String) claims.get("role");
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(role);

            String email = claims.getSubject();

            if (!StringUtils.isEmpty(email)) {
                User user = userService.getByMail(email);

                return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
            }
            return null;
        }
        return null;
    }

}
