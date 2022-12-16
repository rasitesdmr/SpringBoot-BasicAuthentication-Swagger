package com.example.springbootbasicauthenticationswagger.configuration;

import com.example.springbootbasicauthenticationswagger.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity  // Note SecurityConfiguration 1
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    final Environment env;


    @Bean  // Note SecurityConfiguration 2
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/listProduct")
                .hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/createProduct")
                .hasAnyAuthority("ADMIN")
                .antMatchers(env.getProperty("auth.white-list", String[].class)).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }

    @Bean // Note SecurityConfiguration 2
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(customUserDetailsService);

        return authenticationManagerBuilder.build();

    }

    @Bean // Note SecurityConfiguration 2
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
