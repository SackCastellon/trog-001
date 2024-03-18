package org.tfoc;

/**
 * The rental class represents a customer renting a movie.
 */
public record Rental(Movie movie, int daysRented) {
    public double price() {
        return movie.category().calculatePrice(daysRented);
    }

    public int points() {
        return movie.category().calculatePoints(daysRented);
    }
}
