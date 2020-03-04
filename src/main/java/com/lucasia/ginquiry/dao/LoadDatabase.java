package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.service.BoozeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@Log4j2
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatbase(BoozeService boozeService) {
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
