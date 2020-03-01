package com.lucasia.ginquiry.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
/*
 * Need to add Year, Botanical (ingredients), Notes (taste), Geography/Location (Japanese, Highland, Isley)
 */
public class Booze implements Nameable {

    public static final Booze ROCK_ROSE_SPRING =
            new Booze(Brand.ROCK_ROSE, "Spring Edition", "A crisp, fresh taste of spring with a long smooth finish");

    public static final Booze ROCK_ROSE_WINTER =
            new Booze(Brand.ROCK_ROSE, "Winter Edition", "A juniper led earthy gin with fragrant pine notes.");


    private @Id @GeneratedValue(generator = "increment") Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private String description;

    public Booze(@NonNull Brand brand, @NonNull String name, @NonNull String description) {
        this.brand = brand;
        this.name = name;
        this.description = description;
    }

    public Booze() {
    }
}
