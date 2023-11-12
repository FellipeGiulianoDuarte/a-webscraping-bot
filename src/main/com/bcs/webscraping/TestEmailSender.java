package com.bcs.webscraping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EmailSenderTest {

    @BeforeEach
    void setUp() {
        System.setProperty("java.util.logging.ConsoleHandler.level", "OFF"); // Disable annoying log messages
    }

    @Test
    void setEmailRecipient() {
        EmailSender emailSender = new EmailSender();
        String userEmail = "test@example.com";

        emailSender.setEmailRecipient(userEmail);

        Assertions.assertEquals(userEmail, emailSender.userEmail);
    }

    @Test
    void setSelectedProducts() {
        EmailSender emailSender = new EmailSender();
        List<Product> products = new ArrayList<>();

        emailSender.setSelectedProducts(products);

        Assertions.assertEquals(products, emailSender.selectedProducts);
    }

    @Test
    void formatEmailContent() {
        EmailSender emailSender = new EmailSender();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Test Product", "19.99"));
        emailSender.setSelectedProducts(products);

        String formattedContent = emailSender.formatEmailContent();

        // Add assertions to verify the formatted content
        Assertions.assertTrue(formattedContent.contains("Test Product"));
        Assertions.assertTrue(formattedContent.contains("19.99"));
    }

    // Note: It's challenging to test emailProductDetails without sending real emails

    // Commenting out this test as it may send actual emails, and it's hard to simulate
    // real SMTP server behavior in a unit test environment.
    /*
    @Test
    void emailProductDetails() throws MessagingException {
        EmailSender emailSender = new EmailSender();
        emailSender.setEmailRecipient("test@example.com");
        List<Product> products = new ArrayList<>();
        products.add(new Product("Test Product", 19.99));
        emailSender.setSelectedProducts(products);

        Assertions.assertDoesNotThrow(() -> emailSender.emailProductDetails());
    }
    */
}