package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.domain.User;
import com.lucasia.ginquiry.service.BoozeService;
import com.lucasia.ginquiry.security.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
@Log4j2
public class LoadDatabase {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner initUsers(UserService userService) {
        return args -> {

            final User guestUser = new User("guest", passwordEncoder().encode("guest"), true);
            final User writeUser = new User("ginquiry_write", passwordEncoder().encode("ginquiry_write"), true);

            final List<User> users = Arrays.asList(guestUser, writeUser);
            try {
                userService.getUserRepository().saveAll(users);
            } catch (Exception e) {
                log.warn("Preloading failed!! Unable to load " + Arrays.toString(users.toArray()));
            }

        };
    }

    @Bean
    CommandLineRunner initGins(BoozeService boozeService) {
        return args -> {

            final List<Brand> brands = Arrays.asList(Brand.HENDRICKS, Brand.ROCK_ROSE);
            try {
                boozeService.getBrandRepository().saveAll(brands);
            } catch (Exception e) {
                log.warn("Preloading failed!! Unable to load " + Arrays.toString(brands.toArray()));
            }


            final List<Booze> gins = Arrays.asList(Booze.ROCK_ROSE_SPRING, Booze.ROCK_ROSE_WINTER);
            try {
                boozeService.saveAll(gins);
            } catch (Exception e) {
                log.warn("Preloading failed!! Unable to load " + Arrays.toString(gins.toArray()));
            }

        };
    }

}
