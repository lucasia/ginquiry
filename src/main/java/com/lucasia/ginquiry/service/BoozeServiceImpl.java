package com.lucasia.ginquiry.service;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoozeServiceImpl implements BoozeService {

    private BoozeRepository boozeRepository;

    private BrandRepository brandRepository;

    public BoozeServiceImpl(@NonNull BoozeRepository boozeRepository, @NonNull BrandRepository brandRepository) {
        this.boozeRepository = boozeRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Booze save(@NonNull Booze booze) {
        Brand brand = booze.getBrand();

        if (brand == null) throw new NullPointerException("Saving empty Brand on " + booze);

        booze.setBrand(findOrSaveBrand(brand));

        return boozeRepository.save(booze);
    }

    @Override
    public List<Booze> saveAll(Iterable<Booze> boozeList) {

        // first find or save the brands
        // TODO: this will be slow with a large list of Booze.  could (should) be batched.
        for (Booze booze : boozeList) {
            booze.setBrand(findOrSaveBrand(booze.getBrand()));
        }

        return boozeRepository.saveAll(boozeList);
    }

    public Brand findOrSaveBrand(Brand brand) {
        if (brand.getId() != null) return brand; // we have the persistent Brand already

        final Brand persistedBrand = brandRepository.findByName(brand.getName()); // check the db if Brand exists

        return persistedBrand != null ? persistedBrand : brandRepository.saveAndFlush(brand); // return existing or newly persisted Brand
    }

    @Override
    public BoozeRepository getBoozeRepository() {
        return boozeRepository;
    }

    @Override
    public BrandRepository getBrandRepository() {
        return brandRepository;
    }
}
