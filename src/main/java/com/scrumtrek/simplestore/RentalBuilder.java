package com.scrumtrek.simplestore;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RentalBuilder {
    private Movie movie;
    private int daysRented;
    
    public RentalBuilder withMovie(Movie movie) {
        this.movie = movie;
        return this;
    }
    

    public RentalBuilder withDaysRented(int daysRented) {
        this.daysRented = daysRented;
        return this;
    }
    
    public Rental build() {
        Rental stubRental = mock(Rental.class);
        
        when(stubRental.getMovie()).thenReturn(movie);
        when(stubRental.getDaysRented()).thenReturn(daysRented);
        

        return stubRental;
    }
}
