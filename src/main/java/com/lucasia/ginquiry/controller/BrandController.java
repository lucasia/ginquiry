package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BrandController.BRAND_PATH)
@Log4j2
/*
    curl -X POST localhost:8080/brands -H 'Content-type:application/json' -d '{"name": "A Brand Name"}'
 */
public class BrandController extends AbstractController<Brand, Long> {

    public static final String BRAND_PATH = "/brands";

    public BrandController(BrandRepository repository) {
        super(repository);
    }

}
