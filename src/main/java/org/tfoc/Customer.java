package org.tfoc;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public final class Customer {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name) {
        this(name, List.of());
    }

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = new ArrayList<>(rentals);
    }

    public List<Rental> getRentals() {
        return Collections.unmodifiableList(rentals);
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getStatement() {
        var totalAmount = 0.0;
        var totalPoints = 0;

        var builder = new StringBuilder();
        builder.append("Rental Record for %s%n".formatted(getName()));

        for (var rental : getRentals()) {
            var price = rental.price();

            totalAmount += price;
            totalPoints += rental.points();

            builder.append("\t%s\t%.1f%n".formatted(rental.movie().title(), price));
        }

        // add footer lines
        builder.append("Amount owed is %s%n".formatted(totalAmount));
        builder.append("You earned %d frequent renter points".formatted(totalPoints));

        return builder.toString();
    }
}
