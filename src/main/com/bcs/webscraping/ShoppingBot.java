package com.bcs.webscraping;

import java.util.List;
import java.util.Scanner;

public class ShoppingBot {
    private BrowserManager browserManager;
    private ProductSelector productSelector;
    private EmailSender emailSender;

    public ShoppingBot(BrowserManager browserManager, ProductSelector productSelector, EmailSender emailSender) {
        this.browserManager = browserManager;
        this.productSelector = productSelector;
        this.emailSender = emailSender;
    }

    public void runAutomation() {
        // Initialize the browser and perform automation steps
        browserManager.initializeBrowser();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter keywords to search for products: ");
            String keywords = scanner.nextLine();

            int n;
            do {
                System.out.print("Enter the number of products to select (between 1 and 10): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    System.out.print("Enter the number of products to select (between 1 and 10): ");
                    scanner.next(); // consume the invalid input
                }
                n = scanner.nextInt();

                if (n < 1 || n > 10) {
                    System.out.println("Please enter a number between 1 and 10.");
                }
            } while (n < 1 || n > 10);

            // Perform product selection and get the list of selected products
            productSelector.searchAndSelectProducts(keywords, n+1);
            List<Product> selectedProducts = productSelector.getSelectedProducts();

            // Ask the user for the email address to send product details
            System.out.print("Enter your email address to receive product details: ");
            String userEmail = scanner.next();

            // Set the selected products in the EmailSender
            emailSender.setSelectedProducts(selectedProducts);

            // Set the email recipient in the EmailSender
            emailSender.setEmailRecipient(userEmail);

            // Email the details of the selected products to the user
            emailSender.emailProductDetails();

        } finally {
            // Close the browser after completing the automation
            browserManager.closeBrowser();
        }
    }
}
