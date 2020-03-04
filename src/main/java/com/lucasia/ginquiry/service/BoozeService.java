package com.lucasia.ginquiry.service;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.dao.BrandRepository;
import com.lucasia.ginquiry.domain.Booze;

import java.util.List;

public interface BoozeService  {

    public Booze save(Booze booze);

    public BoozeRepository getBoozeRepository();

    public BrandRepository getBrandRepository();

    public List<Booze> saveAll(Iterable<Booze> boozeList);
}
