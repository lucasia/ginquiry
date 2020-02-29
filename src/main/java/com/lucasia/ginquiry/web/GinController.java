package com.lucasia.ginquiry.web;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(GinController.GIN_PATH)
public class GinController extends AbstractController<Booze, Long> {

    public static final String GIN_PATH = "/gins";

    public GinController(BoozeRepository boozeRepository) {
        super(boozeRepository);
    }

}
