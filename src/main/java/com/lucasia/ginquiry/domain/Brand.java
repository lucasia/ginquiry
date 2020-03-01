package com.lucasia.ginquiry.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
/*
    // TODO renamed Brand to distillery?
 */
@SequenceGenerator(name="brand_generator", sequenceName = "brand_seq", allocationSize=50)
public class Brand implements Nameable {

    public static String BRAND_NAME = "Rock Rose";
    public static final Brand ROCK_ROSE = new Brand(BRAND_NAME);
    public static final Brand HENDRICKS = new Brand("Hendrick's");

    @Id @GeneratedValue private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    public Brand(@NonNull  String name) {
        this.name = name;
    }

    public Brand() {
    }

}
