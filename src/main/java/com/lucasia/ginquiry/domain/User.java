package com.lucasia.ginquiry.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User implements Nameable {

    @Id @GeneratedValue private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String username;

    @NonNull
    @Column(nullable = false, unique = true)
    private String password;

    private boolean enabled;

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User() {
    }

    @Override
    @JsonIgnore
    public String getName() {
        return username;
    }
}
