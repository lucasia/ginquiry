package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class UserRepositoryTest extends AbstractRepositoryTest<User>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public NameableRepository<User, Long> getNameableRepository() {
        return userRepository;
    }

    @Override
    public User newInstance(String name) {
        return new User(name, UUID.randomUUID().toString(), true);

    }
}
