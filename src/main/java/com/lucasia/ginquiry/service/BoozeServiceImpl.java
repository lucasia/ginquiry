package com.lucasia.ginquiry.service;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BoozeServiceImpl implements BoozeService {

    private BoozeRepository boozeRepository;

    private BrandRepository brandRepository;

    public BoozeServiceImpl(BoozeRepository boozeRepository, BrandRepository brandRepository) {
        this.boozeRepository = boozeRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Booze save(@NonNull Booze booze) {
        Brand brand = booze.getBrand();

        if (brand == null) throw new NullPointerException("Saving empty Brand on " + booze);

        if (brand.getId() == null) {
            brandRepository.saveAndFlush(brand);
        }

        return boozeRepository.save(booze);
    }

    @Override
    public BoozeRepository getBoozeRepository() {
        return boozeRepository;
    }


}
