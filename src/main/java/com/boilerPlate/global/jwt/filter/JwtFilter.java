package com.boilerPlate.global.jwt.filter;

import com.boilerPlate.global.utils.JwtUtil;
import com.boilerPlate.login.CustomUserDetails;
import com.boilerPlate.user.infrastructure.domain.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//요청에 한번만 실행
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            //필터 종료
            return;
        }

        String token = authorization.split(" ")[1];

        //토큰 소멸시간 검증 true 소멸, false 소멸 되지 않음
        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            //필터 종료
            return;
        }

        String userId = jwtUtil.getUserId(token);
        Users user = Users.builder().userId(userId).build();

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
