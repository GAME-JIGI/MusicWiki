package com.gamejigi.wiki.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    //소셜로그인 사용하면 주석 해제
    //private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomUserService customUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions((FrameOptionsConfig::disable)))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .logout(configurer -> configurer.invalidateHttpSession(true))
                .userDetailsService(customUserService);

        //소셜로그인 사용하면 주석 해제
        //.oauth2Login(loginConfigurer ->
        // loginConfigurer.userInfoEndpoint(endpointConfig ->
        // endpointConfig.userService(customOAuth2UserService)));

        return http.build();
    }

}