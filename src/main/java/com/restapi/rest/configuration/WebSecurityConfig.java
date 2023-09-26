package com.restapi.rest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@PreAuthorize("hasRole('ADMIN')")
public class WebSecurityConfig {
    private final SuccesUserHandler succesUserHandler;

    @Autowired
    public WebSecurityConfig(SuccesUserHandler succesUserHandler) {
        this.succesUserHandler = succesUserHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((req) -> req.requestMatchers("/admin**").hasAuthority("ADMIN")
                        .requestMatchers("/user**","/api/user/**").hasAnyAuthority("ADMIN","USER")
                        .anyRequest().authenticated()
                ).formLogin((form) -> form.permitAll()
                        .permitAll()
                        .successHandler(succesUserHandler))
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutSuccessUrl("/login")
                        .permitAll());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
