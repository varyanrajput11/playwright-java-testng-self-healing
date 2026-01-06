package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import healing.HealBy;
import org.testng.Assert;

public class ProductsPage extends BasePage {

    @HealBy(
        friendlyName = "Products Title",
        primary = "css=.title",
        fallbacks = { "text=Products" }
    )
    private Locator title;

    public ProductsPage(Page page) {
        super(page);
        bindHealingLocators(); // now page should already be on Products screen
    }

    public void assertLoaded() {
        Assert.assertEquals(title.textContent().trim(), "Products");
    }
}