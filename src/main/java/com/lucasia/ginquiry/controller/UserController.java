package com.lucasia.ginquiry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(UserController.PRINCIPAL_PATH)
public class UserController {

    public static final String PRINCIPAL_PATH = "/principal";

    @GetMapping
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}
