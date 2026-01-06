package listeners;

import core.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        if (!(instance instanceof BaseTest)) return;

        new File("artifacts").mkdirs();

        BaseTest test = (BaseTest) instance;
        String name = result.getMethod().getMethodName();

        try { test.saveScreenshot(name); } catch (Exception ignored) {}
        try { test.saveTrace(name); } catch (Exception ignored) {}
    }

    @Override
    public void onFinish(ITestContext context) {
        // optional
    }
}
