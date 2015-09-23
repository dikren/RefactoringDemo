package com.scrumtrek.simplestore;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieBuilder {
    private String title;
    private PriceCodes priceCode;
    
    public MovieBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    

    public MovieBuilder withPriceCode(PriceCodes pc) {
        this.priceCode = pc;
        return this;
    }
    
    public Movie build() {
        Movie stubMovie = mock(Movie.class);
        when(stubMovie.getTitle()).thenReturn(title);
        when(stubMovie.getPriceCode()).thenReturn(priceCode);
        
        return stubMovie;
    }

}
