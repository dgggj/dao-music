package com.che.smartkitchen.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.che.smartkitchen.config.SecurityConfig;
import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;

    }

    UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("1");

        String header = request.getHeader(SecurityConfig.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConfig.TOKEN_PREFIX)) {
            System.out.println("14");
            chain.doFilter(request, response);
            return;
        }
        System.out.println("2");

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        System.out.println("3");
        if (header != null) {
            String username = JWT.require(Algorithm.HMAC512(SecurityConfig.SECRET))
                    .build()
                    .verify(header.replace(SecurityConfig.TOKEN_PREFIX, ""))
                    .getSubject();
            if (username != null) {
                User user = userService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            }
        }
        return null;

    }
}

