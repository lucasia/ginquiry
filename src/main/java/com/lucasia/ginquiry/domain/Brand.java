package com.lucasia.ginquiry.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Brand {

    public static String BRAND_NAME = "Rock Rose";
    public static final Brand ROCK_ROSE = new Brand(BRAND_NAME);
    public static final Brand HENDRICKS = new Brand("Hendrick's");

    @Getter
    private @Id @GeneratedValue Long brandId;

    @Getter
    private String brandName;

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    public Brand() {
    }
}
