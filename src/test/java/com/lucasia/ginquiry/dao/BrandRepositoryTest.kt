package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Brand
import com.lucasia.ginquiry.domain.DomainFactory
import org.springframework.beans.factory.annotation.Autowired

class BrandRepositoryTest : AbstractRepositoryTest<Brand>(DomainFactory.BrandDomainFactory()) {

    @Autowired
    val brandRepository: BrandRepository? = null

    override fun getRepository(): BrandRepository? {
        return brandRepository;
    }
}
