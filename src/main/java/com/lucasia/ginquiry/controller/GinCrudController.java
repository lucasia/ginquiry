package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.service.BoozeService;
import com.lucasia.ginquiry.service.BoozeServiceImpl;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GinCrudController.GIN_PATH)
@Log4j2
/*
   curl -v localhost:8081/gins

    curl -X POST localhost:8081/gins -H 'Content-type:application/json' -d \
        '{"name":"A Booze Name","description":"Test Booze Description","brand":{"name":"A Brand Name"}}'
 */
public class GinCrudController extends AbstractCrudController<Booze, Long, BoozeRepository> {

    public static final String GIN_PATH = "/gins";

    private BoozeService boozeService;

    public GinCrudController(@NonNull BoozeRepository boozeRepository, @NonNull BrandRepository brandRepository) {
        super(boozeRepository);

        this.boozeService = new BoozeServiceImpl(boozeRepository, brandRepository);
    }

    @PostMapping()
    Booze newEntity(@RequestBody Booze newEntity) {
        return boozeService.save(newEntity);
    }

}
