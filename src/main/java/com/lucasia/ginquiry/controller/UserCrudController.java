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
    curl -v localhost:8081/gins

    curl -X POST localhost:8081/brands -H 'Content-type:application/json' -d '{"name": "A Brand Name"}'
 */
public class UserCrudController extends AbstractCrudController<User, Long> {

    public static final String USER_PATH = "/user";

    public UserCrudController(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    List<User> all() {
        throw new UnsupportedOperationException("Find all Users not supported");
    }
}
