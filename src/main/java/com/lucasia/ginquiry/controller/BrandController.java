package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.BrandClient;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BrandController.BRAND_PATH)
@Log4j2
public class BrandController extends AbstractController<Brand, Long> {

    public static final String BRAND_PATH = "/brands";

    public BrandController(BrandClient brandClient) {
        super(brandClient);
    }

}
