package com.lucasia.ginquiry.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
public class Booze {

    static String NAME = "Winter Edition";
    static String DESCRIPTION = "A juniper led earthy gin with fragrant pine notes.";
    static final Booze ROCK_ROSE_WINTER = new Booze(1L, Brand.ROCK_ROSE, NAME, DESCRIPTION);

    private @Id @GeneratedValue Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private String description;

    public Booze(@NonNull Long id, @NonNull Brand brand, @NonNull String name, @NonNull String description) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.description = description;
    }

    public Booze() {
    }
}
