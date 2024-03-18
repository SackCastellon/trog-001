package org.tfoc;

/**
 * The rental class represents a customer renting a movie.
 */
public record Rental(Movie movie, int daysRented) {}
