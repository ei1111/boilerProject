package com.boilerPlate.global.jwt.filter;


import com.boilerPlate.global.utils.JwtUtil;
import com.boilerPlate.login.CustomUserDetails;
import com.boilerPlate.login.request.LoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/v1/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = null;

        try {
            LoginRequestDto loginRequestDto  = new ObjectMapper().readValue(request.getReader(), LoginRequestDto.class);
            String userId = loginRequestDto.getUserId();
            String password = loginRequestDto.getPassword();
            authToken = new UsernamePasswordAuthenticationToken(userId, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //authenticationManager에서 검증진행(디비에서 회원정보를 땡겨와서 UserDetails 서비스에서 유저정보를 받고 검증진행)
        return authenticationManager.authenticate(authToken);
    }

    //authenticationManager에서 검증 성공시 실행
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        String token = jwtUtil.createJwt(userId);
        response.addHeader("Authorization", "Bearer " + token);
    }

    //authenticationManager에서 검증 실패시 실행
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }
}
