package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
