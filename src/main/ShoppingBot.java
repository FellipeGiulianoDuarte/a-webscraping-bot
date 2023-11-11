//TODO: chatGPT fez essa BOMBA de código, revisar e testar isso

//TODO: montar um package

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
            System.out.print("Enter the shopping website URL (e.g., www.amazon.com): ");
            String websiteURL = scanner.nextLine();

            // Assuming the user enters the website URL
            browserManager.getBrowser().newPage().navigate(websiteURL);

            System.out.print("Enter keywords to search for products: ");
            String keywords = scanner.nextLine();

            System.out.print("Enter the number of products to select (between 1 and 10): ");
            int n = scanner.nextInt();

            // Perform product selection and get the list of selected products
            productSelector.searchAndSelectProducts(keywords, n);
            List<Product> selectedProducts = productSelector.getSelectedProducts();

            // Ask the user for the email address to send product details
            System.out.print("Enter your email address to receive product details: ");
            String userEmail = scanner.next();

            // Set the email recipient in the EmailSender
            emailSender.setEmailRecipient(userEmail);

            // Email the details of the selected products to the user
            emailSender.emailProductDetails(selectedProducts.toString());

        } finally {
            // Close the browser after completing the automation
            browserManager.closeBrowser();
        }
    }

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
