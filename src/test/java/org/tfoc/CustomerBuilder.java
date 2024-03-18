package org.tfoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerBuilder {

    public static final String NAME = "Gregroire";
    private String name = NAME;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer build() {
        return new Customer(name, rentals);
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withRentals(Rental... rentals) {
        Collections.addAll(this.rentals, rentals);
        return this;
    }

}
