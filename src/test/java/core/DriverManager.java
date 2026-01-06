package core;
import com.microsoft.playwright.*;

public class DriverManager {
	private Playwright playwright;
    private Browser browser;

    public BrowserContext createContext(boolean headless) {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        return browser.newContext();
    }

    public void close() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
