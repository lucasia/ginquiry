package com.lucasia.ginquiry.service;

import com.lucasia.ginquiry.dao.BoozeRepository;
import com.lucasia.ginquiry.domain.Booze;

public interface BoozeService  {

    public Booze save(Booze booze);

    public BoozeRepository getBoozeRepository();
}
