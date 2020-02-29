package com.lucasia.ginquiry.domain;

import lombok.Data;

@Data
public class Booze {

    static String NAME = "Winter Edition";
    static String DESCRIPTION = "A juniper led earthy gin with fragrant pine notes.";
    static final Booze ROCK_ROSE_WINTER = new Booze(1L, Brand.ROCK_ROSE, NAME, DESCRIPTION);

    private final Long boozeId;
    private final Brand brand;
    private final String name;
    private final String description;

    public Booze(Long boozeId, Brand brand, String name, String description) {
        this.boozeId = boozeId;
        this.brand = brand;
        this.name = name;
        this.description = description;
    }
}
