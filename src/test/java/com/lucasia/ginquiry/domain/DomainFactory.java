package com.lucasia.ginquiry.domain;

import java.util.UUID;

public abstract class DomainFactory<T> {

    public abstract T newInstance(String name);

    public  T newInstanceRandomName() {
        return newInstance(UUID.randomUUID().toString());
    }

    public static class BrandDomainFactory extends DomainFactory<Brand> {

        @Override
        public Brand newInstance(String name) {
            return new Brand(name);
        }
    }

    public static class BoozeDomainFactory extends DomainFactory<Booze> {
        private Brand brand;

        public BoozeDomainFactory(Brand brand) {
            this.brand = brand;
        }

        @Override
        public Booze newInstance(String name) {
            return new Booze(brand, name, "desc: " + name);
        }
    }

    public static class UserDomainFactory extends DomainFactory<User> {

        @Override
        public User newInstance(String name) {
            return new User(name, UUID.randomUUID().toString(), true);
        }
    }

}
