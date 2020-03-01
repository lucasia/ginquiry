package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import com.lucasia.ginquiry.service.BoozeService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GinController.GIN_PATH)
@Log4j2
public class GinController extends AbstractController<Booze, Long>{

    public static final String GIN_PATH = "/gins";

    private BoozeRepository boozeRepository;

    private BrandRepository brandRepository;


    public GinController(BoozeRepository boozeRepository, BrandRepository brandRepository) {
        super(boozeRepository);
        this.brandRepository = brandRepository;
        this.boozeRepository = boozeRepository;
    }

    /*
    public GinController(BoozeService boozeService) {
        this.boozeService = boozeService;
    }


    List<Booze> all() {
        return getRepository().findAll();
    }

    Booze newEntity(Booze newEntity) {
        return getRepository().save(newEntity);
    }

    Booze one(Long aLong) {
        throw new RuntimeException("not implement");

        //return super.one(aLong);
    }

    public BoozeRepository getRepository() {
        return boozeService.getBoozeRepository();
    }

     */

    public Booze save(@NonNull Booze booze) {
        Brand brand = booze.getBrand();

        if (brand == null) throw new NullPointerException("Saving empty Brand on " + booze);

        if (brand.getId() == null) {
            brandRepository.saveAndFlush(brand);
        }

        return boozeRepository.save(booze);
    }
}
