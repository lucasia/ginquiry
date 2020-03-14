package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Brand;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.hasItem;

public class BrandRepositoryTest extends AbstractRepositoryTest<Brand, BrandRepository>{

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandRepository getRepository() {
        return brandRepository;
    }

    @Override
    public Brand newInstance(String name) {
        return new Brand(name);
    }
}
