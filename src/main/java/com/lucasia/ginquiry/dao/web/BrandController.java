package com.lucasia.ginquiry.dao.web;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping(BrandController.BRAND_PATH)
public class BrandController {

    private final BrandRepository brandRepository;
    public static final String BRAND_PATH = "/brands";


    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // aggregate root

    @GetMapping()
    List<Brand> allBrands(){
        return brandRepository.findAll();
    }

    @PostMapping()
    Brand newBrand(@RequestBody Brand newBrand) {
        return brandRepository.save(newBrand);
    }

    // single item

    @GetMapping("/{id}")
    Brand one(@PathVariable Long id) {

        log.debug("finding " + id);

        Optional<Brand> byId = brandRepository.findById(id);

        return byId.orElseThrow(() -> new ResourceNotFoundException(Brand.class, id));
   }

}
