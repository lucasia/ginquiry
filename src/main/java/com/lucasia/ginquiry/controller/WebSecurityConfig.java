package com.lucasia.ginquiry.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// TODO: replace by passing in Cross Site Request Forgery token
                                // see https://spring.io/blog/2013/08/21/spring-security-3-2-0-rc1-highlights-csrf-protection/
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .httpBasic()
                    .and()
                .logout()
                    .permitAll()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");


    }
}