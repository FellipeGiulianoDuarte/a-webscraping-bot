package com.bcs.webscraping;

import com.microsoft.playwright.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrowserManagerTest {

    private BrowserManager browserManager;

    @BeforeEach
    void setUp() {
        browserManager = new BrowserManager();
    }

    @AfterEach
    void tearDown() {
        browserManager.closeBrowser();
    }

    @Test
    void initializeBrowser() {
        assertNotNull(browserManager.getBrowserContext());
    }

    @Test
    void getBrowserContext() {
        assertNotNull(browserManager.getBrowserContext());
    }

    @Test
    void closeBrowser() {
        // Assuming the browser is initially not closed
        assertFalse(isBrowserClosed(browserManager.getBrowser()));

        // Close the browser
        browserManager.closeBrowser();

        // Verify that the browser is closed
        assertTrue(isBrowserClosed(browserManager.getBrowser()));
    }

    private boolean isBrowserClosed(Browser browser) {
        try {
            // Attempting to perform an action that would throw an exception if the browser is closed
            browser.newContext();
            return false; // Browser is not closed
        } catch (Exception e) {
            return true; // Browser is closed
        }
    }
}
