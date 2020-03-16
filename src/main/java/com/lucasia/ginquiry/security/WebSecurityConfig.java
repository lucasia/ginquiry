package com.lucasia.ginquiry.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.lucasia.ginquiry.controller.UserController.PRINCIPAL_PATH;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    // TODO: add authorization: http.authorizeRequests().antMatchers(&quot;/**&quot;).hasRole(&quot;USER&quot;).and().httpBasic();
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// TODO: replace by passing in Cross Site Request Forgery token
                                // see https://spring.io/blog/2013/08/21/spring-security-3-2-0-rc1-highlights-csrf-protection/
                .authorizeRequests()
                    .antMatchers(PRINCIPAL_PATH).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .httpBasic()
                    .and()
                .logout()
                    .permitAll()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");


    }
}