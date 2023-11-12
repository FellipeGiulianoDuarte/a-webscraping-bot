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
}
