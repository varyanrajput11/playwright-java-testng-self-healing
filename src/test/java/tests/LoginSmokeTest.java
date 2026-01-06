package tests;

import core.BaseTest;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

@Listeners(TestListener.class)
public class LoginSmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void login_should_navigate_to_products() {
    	LoginPage login = new LoginPage(page);
    	login.open();
    	login.login("standard_user", "secret_sauce");

    	ProductsPage products = new ProductsPage(page);
    	products.assertLoaded();
    }
}
