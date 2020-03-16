package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.Booze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoozeRepository extends NameableJpaRepository<Booze, Long> {

    @Query("SELECT booze FROM Booze booze where booze.name = :name")
    Booze findByName(@Param("name") String name);
}
