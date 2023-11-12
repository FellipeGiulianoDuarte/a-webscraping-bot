package com.bcs.webscraping;

public class Product {
    // Fields
    private String title;
    private String price;

    // Constructors
    public Product(String title, String price) {
        this.title = title;
        this.price = price;
    }

    // Methods
    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
