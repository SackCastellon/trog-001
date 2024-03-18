package org.tfoc;

import java.util.ArrayList;
import java.util.List;

import static org.tfoc.Category.NEW_RELEASE;

public class Customer {

    private String _name;
    private List<Rental> _rentals = new ArrayList<Rental>();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

        for (Rental each : _rentals) {
            double thisAmount = each.movie().category().calculatePrice(each.daysRented());

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.movie().category() == NEW_RELEASE) && each.daysRented() > 1) {
                frequentRenterPoints++;
            }

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
