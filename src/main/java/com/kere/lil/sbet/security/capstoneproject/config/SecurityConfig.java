package com.kere.lil.sbet.security.capstoneproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/signup", "/login", "/styles.css").permitAll()  // Allow public access to these paths
                .anyRequest().authenticated()  // Require authentication for any other request
                .and()
                .formLogin()
                .loginPage("/login")  // Set your custom login page
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
