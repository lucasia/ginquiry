package com.lucasia.ginquiry.controller;

import com.lucasia.ginquiry.Client;
import com.lucasia.ginquiry.domain.Booze;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GinController.GIN_PATH)
@Log4j2
public class GinController extends AbstractController<Booze, Long> {

    public static final String GIN_PATH = "/gins";

    public GinController(Client<Booze, Long> client) {
        super(client);
    }

}
