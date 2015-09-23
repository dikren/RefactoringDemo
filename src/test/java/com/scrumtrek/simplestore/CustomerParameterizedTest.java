package com.scrumtrek.simplestore;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CustomerParameterizedTest {
    
 
    private PriceCodes priceCode;
    private int daysRentered;
    private String result;
 
    public CustomerParameterizedTest(PriceCodes priceCode, int daysRentered,
            String result) {
        this.priceCode = priceCode;
        this.daysRentered = daysRentered;
        this.result = result;
    }
 
    @Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][] { {PriceCodes.NewRelease, 9, "Rental record for Name\n\tTesting title\t27.0\nAmount owed is 27.0\nYou earned 2 frequent renter points." },
                { PriceCodes.Regular, 9, "Rental record for Name\n\tTesting title\t12.5\nAmount owed is 12.5\nYou earned 1 frequent renter points." } });
    }
 
    @Test
    public void sum() {
        
        String name = "Name";
        Customer sut = new Customer(name);
        Rental stubRental = mock(Rental.class);
        
        stubRental = CustomerTest.createStubRental("Testing title", priceCode, daysRentered);

        sut.addRental(stubRental);

        assertEquals(result, sut.Statement());
    }

}
