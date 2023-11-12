package com.bcs.webscraping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestShoppingBot {

    private ShoppingBot shoppingBot;
    private BrowserManagerStub browserManager;
    private ProductSelectorStub productSelector;
    private EmailSenderStub emailSender;
    private InputStream originalSystemIn;

    @BeforeEach
    void setUp() {
        browserManager = new BrowserManagerStub();
        productSelector = new ProductSelectorStub(browserManager);
        emailSender = new EmailSenderStub();
        shoppingBot = new ShoppingBot(browserManager, productSelector, emailSender);

        // Save the original System.in to restore it later
        originalSystemIn = System.in;
    }

    @Test
    void testRunAutomationHappyPath() {
        // Mock user input
        String userInput = "Laptop\n3\nuser@example.com\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        // Run the automation
        shoppingBot.runAutomation();

        // Verify interactions
        assertTrue(browserManager.isInitializeBrowserCalled());
        assertEquals("Laptop", productSelector.getSearchKeywords());
        assertEquals(4, productSelector.getNumberOfProductsToSelect());
        assertEquals("user@example.com", emailSender.getEmailRecipient());
        assertEquals(productSelector.getSelectedProducts(), emailSender.getSelectedProducts());
        assertTrue(emailSender.isEmailProductDetailsCalled());
        assertTrue(browserManager.isCloseBrowserCalled());
    }

    @Test
    void testRunAutomationInvalidNumberOfProducts() {
        // Mock user input with invalid number of products
        String userInput = "Laptop\n15\n3\nuser@example.com\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        // Run the automation
        shoppingBot.runAutomation();

        // Verify interactions (ensure that it asks for the number of products again)
        assertTrue(browserManager.isInitializeBrowserCalled());
        assertEquals("Laptop", productSelector.getSearchKeywords());
        assertEquals(4, productSelector.getNumberOfProductsToSelect());
        assertEquals("user@example.com", emailSender.getEmailRecipient());
        assertEquals(productSelector.getSelectedProducts(), emailSender.getSelectedProducts());
        assertTrue(emailSender.isEmailProductDetailsCalled());
        assertTrue(browserManager.isCloseBrowserCalled());
    }

    // Add more test cases to cover various scenarios and edge cases

    @Test
    void tearDown() {
        // Restore the original System.in
        System.setIn(originalSystemIn);
    }

    class BrowserManagerStub extends BrowserManager {

        private boolean initializeBrowserCalled = false;
        private boolean closeBrowserCalled = false;

        @Override
        public void initializeBrowser() {
            initializeBrowserCalled = true;
        }

        @Override
        public void closeBrowser() {
            closeBrowserCalled = true;
        }

        public boolean isInitializeBrowserCalled() {
            return initializeBrowserCalled;
        }

        public boolean isCloseBrowserCalled() {
            return closeBrowserCalled;
        }
    }
    class ProductSelectorStub extends ProductSelector {

        private String searchKeywords;
        private int numberOfProductsToSelect;
        private List<Product> selectedProducts;

        public ProductSelectorStub(BrowserManager browserManager) {
            super(browserManager);
        }

        @Override
        public void searchAndSelectProducts(String keywords, int n) {
            this.searchKeywords = keywords;
            this.numberOfProductsToSelect = n;
            // Simulate selecting some products
            selectedProducts = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                String title = "Product " + i;
                String price = String.valueOf(i+Math.random());
                selectedProducts.add(new Product(title, price));
            }
        }

        @Override
        public List<Product> getSelectedProducts() {
            return selectedProducts;
        }

        public String getSearchKeywords() {
            return searchKeywords;
        }

        public int getNumberOfProductsToSelect() {
            return numberOfProductsToSelect;
        }
    }

    class EmailSenderStub extends EmailSender {

        private List<Product> selectedProducts;
        private String emailRecipient;
        private boolean emailProductDetailsCalled = false;

        @Override
        public void setSelectedProducts(List<Product> selectedProducts) {
            this.selectedProducts = selectedProducts;
        }

        @Override
        public void setEmailRecipient(String emailRecipient) {
            this.emailRecipient = emailRecipient;
        }

        @Override
        public void emailProductDetails() {
            emailProductDetailsCalled = true;
        }

        public List<Product> getSelectedProducts() {
            return selectedProducts;
        }

        public String getEmailRecipient() {
            return emailRecipient;
        }

        public boolean isEmailProductDetailsCalled() {
            return emailProductDetailsCalled;
        }
    }
}
