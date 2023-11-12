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

/*    // Main method (for testing)
    public static void main(String[] args) {
        // Creating an instance of Product
        Product laptop = new Product("Laptop", 999.99);

        // Accessing and printing the product details
        System.out.println("Product Title: " + laptop.getTitle());
        System.out.println("Product Price: $" + laptop.getPrice());
    }*/
}
