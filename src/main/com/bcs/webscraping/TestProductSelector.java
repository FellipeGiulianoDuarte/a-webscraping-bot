package com.bcs.webscraping;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductSelectorTest {

    @Test
    void testConstructorAndFields() {
        // Create a stub BrowserManager
        BrowserManager browserManager = new BrowserManagerStub();

        // Create a ProductSelector instance
        ProductSelector productSelector = new ProductSelector(browserManager);

        // Verify that the selectedProducts and productPages lists are initialized
        List<Product> selectedProducts = productSelector.getSelectedProducts();
        List<Page> productPages = productSelector.getProductPages();

        assertEquals(0, selectedProducts.size());
        assertEquals(0, productPages.size());
    }

    // Simple stub implementation for BrowserManager
    private static class BrowserManagerStub extends BrowserManager {
    }
}


