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

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

        for (Rental each : getRentals()) {
            var category = each.movie().category();
            double thisAmount = category.calculatePrice(each.daysRented());

            // add frequent renter points
            frequentRenterPoints += category.calculatePoints(each.daysRented());

            // show figures for this rental
            result += "\t" + each.movie().title() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

        return result;
    }
}
