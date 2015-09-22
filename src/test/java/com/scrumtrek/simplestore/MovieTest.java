package com.scrumtrek.simplestore;

import org.junit.*;
import static org.junit.Assert.*;

public class MovieTest {
    @Test
    public void shouldCreateObhjectWhenCreateObject() {
        String title = "TitleStr";
        PriceCodes pc =PriceCodes.Childrens;
        
        Movie sut = new Movie(title, pc);
        
        assertEquals(title, sut.getTitle());
        assertEquals(pc, sut.getPriceCode());
    }

}
