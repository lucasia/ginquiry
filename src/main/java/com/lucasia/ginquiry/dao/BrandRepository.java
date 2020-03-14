package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BrandRepository extends JpaRepository<Brand, Long>, NameableRepository<Brand> {

    @Query("SELECT brand FROM Brand brand where brand.name = :name")
    Brand findByName(@Param("name") String name);

}
