package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.service.BoozeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@Log4j2
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatbase(BoozeService boozeService, BrandRepository brandRepository) {
        return args -> {


            final List<Brand> brands = Arrays.asList(Brand.HENDRICKS, Brand.ROCK_ROSE);

            try {
                brandRepository.saveAll(brands);
            } catch (Exception e) {
                log.warn("Preloading failed!! Unable to load " + Arrays.toString(brands.toArray()));
            }

            // save these individually
            try {
                boozeService.save(Booze.ROCK_ROSE_SPRING);
            } catch (Exception e) {
                log.warn("Preloading failed!! Unable to load " + Booze.ROCK_ROSE_SPRING);
            }

            try {
                boozeService.save(Booze.ROCK_ROSE_WINTER);
            } catch (Exception e) {
                log.warn("Preloading failed!! Unable to load " + Booze.ROCK_ROSE_WINTER);
            }

        };
    }

}
