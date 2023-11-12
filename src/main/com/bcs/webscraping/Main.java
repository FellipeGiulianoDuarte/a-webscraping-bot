package com.bcs.webscraping;

public class Main {
    public static void main(String[] args) {
        // Create instances of BrowserManager, ProductSelector, and EmailSender
        BrowserManager browserManager = new BrowserManager();
        ProductSelector productSelector = new ProductSelector(browserManager);
        EmailSender emailSender = new EmailSender();

        // Create an instance of ShoppingBot and run the automation
        ShoppingBot shoppingBot = new ShoppingBot(browserManager, productSelector, emailSender);
        shoppingBot.runAutomation();
    }
}
