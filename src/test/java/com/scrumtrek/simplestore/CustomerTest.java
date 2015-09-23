package com.scrumtrek.simplestore;


import static org.junit.Assert.assertEquals;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
    
    private String title ;
    private Customer sut;
    private Rental stubRental;
    
    @Before
    public void setUp() {
        String name = "Name";
        title = "Testing title";
         sut = new Customer(name);
         stubRental = mock(Rental.class);
    }
    
    private void setUpWithParams(PriceCodes pc, int daysRented) {
        stubRental = createStubRental("Testing title", pc, daysRented);
    }
    
    private Rental createStubRental(String movieTitle, PriceCodes pc, int daysRented) {
        Movie stubMovie = mock(Movie.class);
        Rental stubRental = mock(Rental.class);
        
        when(stubMovie.getTitle()).thenReturn(movieTitle);
        when(stubMovie.getPriceCode()).thenReturn(pc);
        
        when(stubRental.getMovie()).thenReturn(stubMovie);
        when(stubRental.getDaysRented()).thenReturn(daysRented);
        
        return stubRental;
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
    
    @Test
    public void shouldFrequentRenterPointsEqual3WhenAdding2RentalsAndOnlyOneOfThemWithNewReleaseAndHaveRentedDayesHigherThen1() {
        setUpWithParams(PriceCodes.NewRelease, 9);
        
        Rental stubRental2 = createStubRental("Second movie title", PriceCodes.NewRelease, 1);
        
        sut.addRental(stubRental);
        sut.addRental(stubRental2);
        
        assertThat(sut.Statement()).contains("You earned 3 frequent renter points");
    }
    

    @Test 
    public void shouldCalculateTotalAmoutRightWhenAddsMultipleRentals() {
        setUpWithParams(PriceCodes.Childrens, 10);
        
        Rental stubRental2 = createStubRental("Second Movie", PriceCodes.Regular, 11000);
        Rental stubRental3 = createStubRental("Third Movie", PriceCodes.NewRelease, 11120);
        Rental stubRental4 = createStubRental("Fourth Movie", PriceCodes.Childrens, 120);
        
        sut.addRental(stubRental);
        sut.addRental(stubRental2);
        sut.addRental(stubRental3);
        sut.addRental(stubRental4);
        
        assertThat(sut.Statement()).contains("50045.0");
    }

}
