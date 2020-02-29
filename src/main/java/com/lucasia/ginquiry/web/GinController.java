package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.domain.Booze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GinController.BRAND_PATH)
public class GinController extends AbstractController<Booze, Long> {

    private final BoozeRepository boozeRepository;
    public static final String BRAND_PATH = "/gins";


    public GinController(BoozeRepository boozeRepository) {
        this.boozeRepository = boozeRepository;
    }

    @Override
    public JpaRepository<Booze, Long> getBrandRepository() {
        return boozeRepository;
    }

}
