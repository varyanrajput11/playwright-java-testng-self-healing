package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import healing.HealBy;
import org.testng.Assert;
import utils.Config;

public class LoginPage extends BasePage {

    @HealBy(
        friendlyName = "Username Field",
        primary = "css=#user-name-broken",
        fallbacks = {
            "css=input[name='user-name']",
            "css=input[placeholder='Username']"
        }
    )
    private Locator username;

    @HealBy(
        friendlyName = "Password Field",
        primary = "css=#password",
        fallbacks = {
            "css=input[name='password']",
            "css=input[placeholder='Password']"
        }
    )
    private Locator password;

    @HealBy(
        friendlyName = "Login Button",
        primary = "css=#login-button",
        fallbacks = {
            "text=Login",
            "css=input[type='submit']"
        }
    )
    private Locator loginBtn;

    private Locator errorMessage;

    public LoginPage(Page page) {
        super(page);
        this.errorMessage = page.locator("[data-test='error']");
    }

    public void open() {
        page.navigate(Config.BASE_URL);
        bindHealingLocators(); // IMPORTANT: bind after the DOM is available
    }

    public void login(String user, String pass) {
        username.fill(user);
        password.fill(pass);
        loginBtn.click();
    }

    public void assertErrorContains(String text) {
        Assert.assertTrue(errorMessage.textContent().contains(text));
    }
}