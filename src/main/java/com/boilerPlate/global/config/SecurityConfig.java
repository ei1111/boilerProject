package com.boilerPlate.global.config;

import com.boilerPlate.global.jwt.filter.JwtFilter;
import com.boilerPlate.global.jwt.filter.LoginFilter;
import com.boilerPlate.global.utils.Aes256;
import com.boilerPlate.global.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Aes256();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //csrf disable
        httpSecurity.csrf((csrf) -> csrf.disable());

        //FormLogin, BasicHttp 비활성화
        httpSecurity.formLogin((form) -> form.disable());
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        httpSecurity.headers().frameOptions().disable();

        //경로별 인가 작업
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "v1/**", "/h2-console/**", "/3o3/**", "/actuator/**").permitAll()
                        .anyRequest()
                        .authenticated()
        );

        //제일 중요한 세션 STATELESS 처리
        httpSecurity.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //커스텀 필터 등록
        httpSecurity.addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);
        httpSecurity.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
