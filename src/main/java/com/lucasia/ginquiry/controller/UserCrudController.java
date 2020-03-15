package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.dao.UserRepository;
import com.lucasia.ginquiry.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@RequestMapping(UserCrudController.USER_PATH)
/*
    curl -v localhost:8081/users
*/
public class UserCrudController extends AbstractCrudController<User, Long, UserRepository> {

    public static final String USER_PATH = "/users";

    public UserCrudController(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    List<User> all() {
        throw new UnsupportedOperationException("Find all Users not supported");
    }
}
