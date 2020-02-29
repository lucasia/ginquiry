package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatbase(BrandRepository brandRepository, BoozeRepository boozeRepository) {
        return args -> {
            log.info("Preloading " + Brand.HENDRICKS);
            brandRepository.save(Brand.HENDRICKS);

            log.info("Preloading " + Brand.ROCK_ROSE);
            brandRepository.save(Brand.ROCK_ROSE);

            log.info("Preloading " + Booze.ROCK_ROSE_WINTER);
            boozeRepository.save(Booze.ROCK_ROSE_WINTER);

            log.info("Preloading " + Booze.ROCK_ROSE_SPRING);
            boozeRepository.save(Booze.ROCK_ROSE_SPRING);

        };
    }

}
