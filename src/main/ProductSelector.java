import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class ProductSelector {
    private BrowserManager browserManager;
    private List<Product> selectedProducts;

    public ProductSelector(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.selectedProducts = new ArrayList<>();
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void searchAndSelectProducts(String keywords, int n) {
        BrowserContext browserContext = browserManager.getBrowserContext();

        // Create a new page within the browser context
        try (Page page = browserContext.newPage()) {
            // Navigate to eBay website
            page.navigate("https://www.ebay.com");

            // Type the keywords into the search bar
            page.fill("input[type='text'][name='_nkw']", keywords);

            // Press Enter to perform the search
            page.keyboard().press("Enter");

            // Wait for the search results to load (you may need to adjust the wait time)
            page.waitForTimeout(5000);

            // Select the top n products
            for (int i = 1; i <= n; i++) {
                // Assuming the product links are displayed in a list with class "s-item"
                String productSelector = ".s-item:nth-child(" + i + ")";
                ElementHandle product = page.querySelector(productSelector);

                if (product != null) {
                    // Extract product details
                    String title = product.querySelector(".s-item__title").innerText();
                    System.out.print(title);
                    String price = product.querySelector(".s-item__price").innerText();
                    System.out.print(price);

                    // Create a Product object and add it to the selected products list
                    Product selectedProduct = new Product(title, parsePrice(price));
                    selectedProducts.add(selectedProduct);
                }
            }
        }
    }

    private double parsePrice(String price) {
        // Extract the numeric part of the price and convert it to a double
        String numericPart = price.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numericPart);
    }
}
