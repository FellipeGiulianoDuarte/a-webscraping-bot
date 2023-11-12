package com.bcs.webscraping;

import com.microsoft.playwright.*;

public class BrowserManager {

    private Browser browser;
    private BrowserContext browserContext;

    public BrowserManager() {
        initializeBrowser();
    }

    public void initializeBrowser() {
        Playwright playwright = Playwright.create();

        // Launch a new browser instance
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        // Create a new browser context
        browserContext = browser.newContext();
    }

    public Browser getBrowser() {
        return browser;
    }

    public BrowserContext getBrowserContext() {
        return browserContext;
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
    }

/*    public static void main(String[] args) {

        //teste funcionando
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.google.com");
        System.out.println("Page title: " + page.title());

        //-------------------------

        BrowserManager browserManager = new BrowserManager();
        BrowserContext browserContext = browserManager.getBrowserContext();

        // Create a new page within the browser context
        Page page = browserContext.newPage();

        // Navigate to Google
        page.navigate("https://www.google.com");

        // Wait for the search input to be available
        page.waitForSelector("input[name='q']");

        ElementHandle searchInput = page.querySelector("input[name='q']");
        searchInput.fill("Playwright Java");

        // Press Enter to submit the search
        page.press("input[name='q']", "Enter");

        // Wait for the search results
        page.waitForLoadState();

        // Output the title of the page
        System.out.println("Page title: " + page.title());

        //browserManager.closeBrowser();
    }*/
}
