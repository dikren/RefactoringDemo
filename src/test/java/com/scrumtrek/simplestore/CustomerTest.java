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
        title = "Testing title";
         sut = new Customer(name);
         stubMovie = mock(Movie.class);
         stubRental = mock(Rental.class);
    }
    
    private void setUpWithParams(PriceCodes pc, int daysRented) {
        when(stubMovie.getTitle()).thenReturn("Testing title");
        when(stubMovie.getPriceCode()).thenReturn(pc);
        
        when(stubRental.getMovie()).thenReturn(stubMovie);
        when(stubRental.getDaysRented()).thenReturn(daysRented);
    }
    
    @Test
    public void shouldReturnRightStringWhenRentalIsNewRelease() {
        setUpWithParams(PriceCodes.NewRelease, 9);
        
        sut.addRental(stubRental);
        
        assertEquals("Rental record for Name\n\tTesting title\t27.0\nAmount owed is 27.0\nYou earned 2 frequent renter points.", sut.Statement());
    }
    
    @Test
    public void shouldReturnRightStringWhenRentalIsRegularAndDaysRentedBiggerThan2() {
        setUpWithParams(PriceCodes.Regular, 9);
        
        
        sut.addRental(stubRental);
        
        assertEquals("Rental record for Name\n\tTesting title\t12.5\nAmount owed is 12.5\nYou earned 1 frequent renter points.", sut.Statement());
    }
    
    @Test
    public void shouldReturnRightStringWhenRentalIsRegularAndDaysRentedLessThan2() {
        setUpWithParams(PriceCodes.Regular, 1);
        
        
        sut.addRental(stubRental);
        
        assertEquals("Rental record for Name\n\tTesting title\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points.", sut.Statement());
    }
    
    @Test
    public void shouldReturnRightStringWhenRentalIsChildrensAndDaysRentedLessThan3() {
        setUpWithParams(PriceCodes.Childrens, 1);

        sut.addRental(stubRental);
        
        assertEquals("Rental record for Name\n\tTesting title\t1.5\nAmount owed is 1.5\nYou earned 1 frequent renter points.",
                sut.Statement());
    }
    

    @Test
    public void shouldReturnRightStringWhenRentalIsChildrensAndDaysRentedBiggerThan3() {
        setUpWithParams(PriceCodes.Childrens, 9);
        
        sut.addRental(stubRental);
        
        assertEquals("Rental record for Name\n\tTesting title\t9.0\nAmount owed is 9.0\nYou earned 1 frequent renter points.",
                sut.Statement());
    }
    
    @Test
    public void shouldCallGetDaysRentedAtLeastOneTimeWhenCallStatement() {
        setUpWithParams(PriceCodes.Childrens, 9);
        
        sut.addRental(stubRental);
        
        sut.Statement();
        
        verify(stubRental, atLeastOnce()).getDaysRented();
        
        
    }

}
