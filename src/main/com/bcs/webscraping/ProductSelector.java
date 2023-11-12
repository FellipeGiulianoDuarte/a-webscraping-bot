package com.bcs.webscraping;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductSelector {
    private BrowserManager browserManager;
    private List<Product> selectedProducts;
    private List<Page> productPages;

    public ProductSelector(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.selectedProducts = new ArrayList<>();
        this.productPages = new ArrayList<>();
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public List<Page> getProductPages() {
        return productPages;
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
            for (int i = 2; i <= n; i++) {
                // Assuming the product links are displayed in a list with class "s-item"
                String productSelector = ".s-item:nth-child(" + i + ")";
                ElementHandle product = page.querySelector(productSelector);

                if (product != null) {
                    // Extract product details
                    String title = product.querySelector(".s-item__title").innerText();
                    System.out.println(title);

                    String price = product.querySelector(".s-item__price").innerText();
                    System.out.println(price);

                    // Extract the href link
                    String link = product.querySelector(".s-item__link").getAttribute("href");
                    System.out.println(link);

                    openLinkInNewTab(link);

                    // Create a Product object and add it to the selected products list
                    Product selectedProduct = new Product(title, price);
                    selectedProducts.add(selectedProduct);
                }
            }
            // Wait for user interaction to close the browser
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to exit the browser...");
            scanner.nextLine();

            // Close all open tabs
            closeAllTabs();
        }
    }

    private void openLinkInNewTab(String link) {
        BrowserContext browserContext = browserManager.getBrowserContext();
        Page page = browserContext.newPage();
        page.navigate(link);
        page.waitForTimeout(3000);
        productPages.add(page);
    }

    void closeAllTabs() {
        // Close all open tabs
        for (Page page : productPages) {
            page.close();
        }
    }
}
