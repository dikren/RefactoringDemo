package com.scrumtrek.simplestore;


import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest {
    
    private String title ;
    private Customer sut;
    
    @Before
    public void setUp() {
        String name = "Name";
        title = "title Ours";
         sut = new Customer(name);
        
    }
    
    @Test
    public void shouldTestRentalWhenTestingIt() {
        
        Movie m = new Movie(title, PriceCodes.NewRelease);
        
        Rental rent = new Rental(m,  9); 
        
        
        sut.addRental(rent);
        
        assertEquals("Rental record for Name\n\ttitle Ours\t27.0\nAmount owed is 27.0\nYou earned 2 frequent renter points.", sut.Statement());
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
