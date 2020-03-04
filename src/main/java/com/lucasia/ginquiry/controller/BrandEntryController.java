package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Brand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class BrandEntryController {

    @Value("${greeting:Hello}")
    private String greeting;

    private BrandRepository brandRepository;

    public BrandEntryController(BrandRepository repository) {
        this.brandRepository = repository;
    }

    @GetMapping("/brand")
    public String brand(Model model) {

        if (model.containsAttribute("name")) {
            String name = (String) model.asMap().get("name");
            model.addAttribute("greeting", String.format("%s %s", greeting, name));
        }

        final List<Brand> brands = brandRepository.findAll();

        model.addAttribute("brands", brands);

        return "brand";
    }

    @PostMapping("/brand/post")
    public String post(@RequestParam String brandName, Model model) {
        if (brandName != null && !brandName.trim().isEmpty()) {
            final Brand brand = new Brand(brandName);
            brandRepository.save(brand);

        }
        return "redirect:/brand";
    }

}
