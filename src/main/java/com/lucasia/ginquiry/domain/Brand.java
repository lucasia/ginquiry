package com.lucasia.ginquiry.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
/*
    // TODO renamed Brand to distillery?
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Brand implements Nameable {

    public static String BRAND_NAME = "Rock Rose";
    public static final Brand ROCK_ROSE = new Brand(BRAND_NAME);
    public static final Brand HENDRICKS = new Brand("Hendrick's");

    @Id @GeneratedValue private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    public Brand(@NonNull  String name) {
        this.name = name;
    }

    public Brand() {
    }

}
