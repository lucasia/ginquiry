package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatbase(BrandRepository repository) {
        return args -> {
            log.info("Preloading " + Brand.ROCK_ROSE);
            repository.save(Brand.ROCK_ROSE);

            log.info("Preloading " + Brand.HENDRICKS);
            repository.save(Brand.HENDRICKS);
        };
    }

}
