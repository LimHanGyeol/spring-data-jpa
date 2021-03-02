package com.tommy.datajpa.account;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Spring Data JPA
 * Value Type Mapping
 * Composite Type Mapping
 */
@NoArgsConstructor
@Embeddable
public class Address {

    private String street;

    private String city;

    private String state;

    private String zipCode;

    public Address(String city, String state, String street, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
