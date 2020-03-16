package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends NameableJpaRepository<User, Long> {

    @Query("SELECT user FROM User user where user.username = :username")
    User findByName(@Param("username") String username);
}
