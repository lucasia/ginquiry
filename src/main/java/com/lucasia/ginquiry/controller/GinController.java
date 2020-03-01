package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.service.BoozeService;
import com.lucasia.ginquiry.service.BoozeServiceImpl;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GinController.GIN_PATH)
@Log4j2
public class GinController extends AbstractController<Booze, Long>{

    public static final String GIN_PATH = "/gins";

    private BoozeService boozeService;

    public GinController(@NonNull BoozeRepository boozeRepository, @NonNull BrandRepository brandRepository) {
        super(boozeRepository);
        this.boozeService = new BoozeServiceImpl(boozeRepository, brandRepository);
    }

    @PostMapping()
    Booze newEntity(@RequestBody Booze newEntity) {
        return boozeService.save(newEntity);
    }

}
