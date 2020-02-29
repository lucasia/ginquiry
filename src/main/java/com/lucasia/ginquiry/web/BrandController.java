package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BrandController.BRAND_PATH)
public class BrandController extends AbstractController<Brand, Long> {

    public static final String BRAND_PATH = "/brands";

    public BrandController(BrandRepository brandRepository) {
        super(brandRepository);
    }
}
