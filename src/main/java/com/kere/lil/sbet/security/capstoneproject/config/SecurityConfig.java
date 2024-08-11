package com.kere.lil.sbet.security.capstoneproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/", "/signup", "/profile", "/findBuddy", "/styles.css")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll();
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/signup", "/login", "/styles.css").permitAll()
//                .antMatchers("/findBuddy", "/profile").authenticated()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin((form) -> {
//                    try {
//                        form.loginPage("/login").permitAll()
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/profile", true)
//                                .failureUrl("/login?error=true")
//                                .permitAll()
//                                .and()
//                                .logout()
//                                .logoutSuccessUrl("/login?logout=true")
//                                .permitAll()
//                                .and()
//                                .csrf().disable();
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                });
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
