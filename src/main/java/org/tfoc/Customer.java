package org.tfoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Customer(String name, List<Rental> rentals) {
    public Customer {
        rentals = new ArrayList<>(rentals);
    }

    public Customer(String name) {
        this(name, List.of());
    }

    @Override
    public List<Rental> rentals() {
        return Collections.unmodifiableList(rentals);
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getStatement() {
        var totalAmount = 0.0;
        var totalPoints = 0;

        var builder = new StringBuilder();
        builder.append("Rental Record for %s%n".formatted(name()));

        for (var rental : rentals()) {
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
