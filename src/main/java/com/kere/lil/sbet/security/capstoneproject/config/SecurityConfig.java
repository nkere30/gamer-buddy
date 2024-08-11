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
                .antMatchers("/findBuddy").authenticated()  // Require authentication for /findBuddy
                .anyRequest().authenticated()  // Require authentication for any other request
                .and()
                .formLogin()
                .loginPage("/login")  // Set your custom login page
                .loginProcessingUrl("/login")  // Ensure this matches your form's action
                .defaultSuccessUrl("/profile", true)  // Redirect to profile page on successful login
                .failureUrl("/login?error=true")  // Redirect to login page with error on failure
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")  // Redirect to login page after logout
                .permitAll();
    }
}
