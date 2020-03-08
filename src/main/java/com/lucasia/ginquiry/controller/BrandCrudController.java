package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping(BrandCrudController.BRAND_PATH)
@Log4j2
/*
    curl -v localhost:8081/gins

    curl -X POST localhost:8081/brands -H 'Content-type:application/json' -d '{"name": "A Brand Name"}'
 */
public class BrandCrudController extends AbstractCrudController<Brand, Long> {

    public static final String BRAND_PATH = "/brands";

    public BrandCrudController(BrandRepository repository) {
        super(repository);
    }


}
