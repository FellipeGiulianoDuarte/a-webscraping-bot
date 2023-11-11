//TODO: montar um package

public class Product {
    // Fields
    private String title;
    private double price;

    // Constructors
    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    // Methods
    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

/*    // Main method (for testing)
      //TODO: testar essa joça
    public static void main(String[] args) {
        // Creating an instance of Product
        Product laptop = new Product("Laptop", 999.99);

        // Accessing and printing the product details
        System.out.println("Product Title: " + laptop.getTitle());
        System.out.println("Product Price: $" + laptop.getPrice());
    }*/
}
