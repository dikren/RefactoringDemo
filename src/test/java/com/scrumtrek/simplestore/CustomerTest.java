package com.scrumtrek.simplestore;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
    
    private String title ;
    private Customer sut;
    private Movie stubMovie;
    private Rental stubRental;
    
    @Before
    public void setUp() {
        String name = "Name";
        title = "title Ours";
         sut = new Customer(name);
         stubMovie = mock(Movie.class);
         stubRental = mock(Rental.class);
    }
    
    @Test
    public void shouldReturnRightStringWhenRentalIsNewRelease() {
        when(stubMovie.getTitle()).thenReturn("Teting title");
        when(stubMovie.getPriceCode()).thenReturn(PriceCodes.NewRelease);
        
        when(stubRental.getMovie()).thenReturn(stubMovie);
        when(stubRental.getDaysRented()).thenReturn(9);
        
        
        sut.addRental(stubRental);
        
        assertEquals("Rental record for Name\n\tTeting title\t27.0\nAmount owed is 27.0\nYou earned 2 frequent renter points.", sut.Statement());
    }
    
    @Test
    public void shouldTestRentalWhenTestingIt12() {
        
        Movie m = new Movie(title, PriceCodes.Regular);
        
        Rental rent = new Rental(m,  9); 
        
        
        sut.addRental(rent);
        
        assertEquals("Rental record for Name\n\ttitle Ours\t12.5\nAmount owed is 12.5\nYou earned 1 frequent renter points.", sut.Statement());
    }
    
    @Test
    public void shouldTestRentalWhenTestingIfgdt() {
        
        Movie m = new Movie(title, PriceCodes.Childrens);
        
        Rental rent = new Rental(m,  9); 
        
        
        sut.addRental(rent);
        
        assertEquals("Rental record for Name\n\ttitle Ours\t9.0\nAmount owed is 9.0\nYou earned 1 frequent renter points.",
                sut.Statement());
    }

}
