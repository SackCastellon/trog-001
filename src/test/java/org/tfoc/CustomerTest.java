package org.tfoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.tfoc.Category.*;

@Slf4j
class CustomerTest {

    @Test
    void testCustomer() {
        Customer c = new CustomerBuilder().build();
        assertNotNull(c);
    }

    @Test
    void testAddRental() {
        Customer customer2 = new CustomerBuilder().withName("Sallie").build();
        Movie movie1 = new Movie("Gone with the Wind", REGULAR);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        customer2.addRental(rental1);
    }

    @Test
    void testGetName() {
        Customer c = new Customer("David");
        assertEquals("David", c.name());
    }

    @Test
    void statementForRegularMovie() {
        Movie movie1 = new Movie("Gone with the Wind", REGULAR);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "\tGone with the Wind\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.getStatement();
        assertEquals(expected, statement);
    }

    @Test
    void statementForNewReleaseMovie() {
        Movie movie1 = new Movie("Star Wars", NEW_RELEASE);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "\tStar Wars\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points";
        String statement = customer2.getStatement();
        assertEquals(expected, statement);
    }

    @Test
    void statementForChildrensMovie() {
        Movie movie1 = new Movie("Madagascar", CHILDREN);
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2
                = new CustomerBuilder()
                .withName("Sallie")
                .withRentals(rental1)
                .build();
        String expected = "Rental Record for Sallie\n" +
                "\tMadagascar\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.getStatement();
        assertEquals(expected, statement);
    }

    @Test
    void statementForManyMovies() {
        Movie movie1 = new Movie("Madagascar", CHILDREN);
        Rental rental1 = new Rental(movie1, 6); // 6 day rental
        Movie movie2 = new Movie("Star Wars", NEW_RELEASE);
        Rental rental2 = new Rental(movie2, 2); // 2 day rental
        Movie movie3 = new Movie("Gone with the Wind", REGULAR);
        Rental rental3 = new Rental(movie3, 8); // 8 day rental
        Customer customer1
                = new CustomerBuilder()
                .withName("David")
                .withRentals(rental1, rental2, rental3)
                .build();
        String expected = "Rental Record for David\n" +
                "\tMadagascar\t6.0\n" +
                "\tStar Wars\t6.0\n" +
                "\tGone with the Wind\t11.0\n" +
                "Amount owed is 23.0\n" +
                "You earned 4 frequent renter points";
        String statement = customer1.getStatement();
        assertEquals(expected, statement);
    }

    //TODO make test for price breaks in code.
}
