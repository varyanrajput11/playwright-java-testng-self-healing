package playwright;

import java.io.IOException;

import com.microsoft.playwright.CLI;

public class PlaywrightBrowserInstall {
    public static void main(String[] args) throws IOException, InterruptedException {
        CLI.main(new String[]{"install"});
    }
}