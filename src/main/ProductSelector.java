//TODO: chatGPT fez essa BOMBA de código, revisar e testar isso

//TODO: montar um package

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
        Page page = browserContext.newPage();

        try {
            // Assuming the website has a search bar with the id "searchBar"
            page.navigate("https://www.example.com"); // Replace with the actual website URL

            // Type the keywords into the search bar
            page.fill("#searchBar", keywords);

            // Press Enter to perform the search
            page.keyboard().press("Enter");

            // Wait for the search results to load (you may need to adjust the wait time)
            page.waitForTimeout(5000);

            // Select the top n products
            for (int i = 1; i <= n; i++) {
                // Assuming the product links are displayed in a list with class "product-list-item"
                String productSelector = ".product-list-item:nth-child(" + i + ") a";
                ElementHandle productLink = page.querySelector(productSelector);

                if (productLink != null) {
                    // Click on the product link to open the details
                    productLink.click();

                    // Wait for the product details page to load (you may need to adjust the wait time)
                    page.waitForTimeout(3000);

                    // Extract product details
                    String title = page.title();
                    String price = (String) page.
                            evaluate("() => document.querySelector('.product-price').textContent");

                    // Create a Product object and add it to the selected products list
                    Product product = new Product(title, Double.parseDouble(price));
                    selectedProducts.add(product);

                    // Navigate back to the search results page for the next iteration
                    page.goBack();

                    // Wait for the search results page to load again
                    page.waitForTimeout(3000);
                }
            }
        } finally {
            // Close the page after selecting products
            page.close();
        }
    }
}
