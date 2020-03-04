package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Controller
@RequestMapping(BrandCrudController.BRAND_PATH)
@Log4j2
/*
    curl -X POST localhost:8080/brands -H 'Content-type:application/json' -d '{"name": "A Brand Name"}'
 */
public class BrandCrudController extends AbstractCrudController<Brand, Long> {

    public static final String BRAND_PATH = "/brands";

    public BrandCrudController(BrandRepository repository) {
        super(repository);
    }

/*
    @GetMapping("/brand")
    public String index(Model model) {

        if (model.containsAttribute("name")) {
            String name = (String) model.asMap().get("name");
            model.addAttribute("greeting", String.format("%s %s", greeting, name));
        }

        // model.addAttribute("messages", client.getMessages().getContent());

        return "brand";
    }
*/

}
