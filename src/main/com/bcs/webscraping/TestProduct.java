package com.bcs.webscraping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestProduct {
    @Test
    public void testCreatingProduct() { //complete Product class test
        Product laptop = new Product("Laptop", "999.99");
        Assertions.assertEquals(laptop.getTitle(), "Laptop");
        Assertions.assertEquals(laptop.getPrice(), "999.99");
    }
}
