package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.DomainFactory;
import com.lucasia.ginquiry.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends AbstractRepositoryTest<User>{

    @Autowired
    private UserRepository userRepository;

    public UserRepositoryTest() {
        super(new DomainFactory.UserDomainFactory());
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

}
