package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import com.lucasia.ginquiry.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoozeRepository extends JpaRepository<Booze, Long> {
}
