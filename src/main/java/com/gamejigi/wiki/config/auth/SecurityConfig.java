package com.gamejigi.wiki.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    //소셜로그인 사용하면 주석 해제
    //private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomUserService customUserService;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions((FrameOptionsConfig::disable)))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(configurer -> configurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login")
                        .usernameParameter("userId")
                        .passwordParameter("passWd")
                        .loginProcessingUrl("/login")
                        .permitAll()
                )
                .logout(configurer -> configurer
                        .deleteCookies()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .userDetailsService(customUserService)
                .authenticationProvider(customAuthenticationProvider);

        //소셜로그인 사용하면 주석 해제
        //.oauth2Login(loginConfigurer ->
        // loginConfigurer.userInfoEndpoint(endpointConfig ->
        // endpointConfig.userService(customOAuth2UserService)));

        return http.build();
    }
}