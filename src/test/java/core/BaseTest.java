package core;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import utils.Config;

import java.nio.file.Paths;

public class BaseTest {

    protected DriverManager driver;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        driver = new DriverManager();
        context = driver.createContext(Config.HEADLESS);
        page = context.newPage();

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (context != null) context.close();
        if (driver != null) driver.close();
    }

    // Helper for listener
    public void saveTrace(String fileName) {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("artifacts/" + fileName + ".zip")));
    }

    public void saveScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("artifacts/" + fileName + ".png"))
                .setFullPage(true));
    }
}
