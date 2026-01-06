package healing;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HealingLocator {

    private final Page page;

    public HealingLocator(Page page) {
        this.page = page;
    }

    public Locator resolve(String friendlyName, String primary, String[] fallbacks) {
        Locator primaryLoc = build(primary);
        if (isUsable(primaryLoc)) return primaryLoc;

        for (String fb : fallbacks) {
            Locator fbLoc = build(fb);
            if (isUsable(fbLoc)) {
                System.out.println("[SELF-HEAL] '" + friendlyName + "' fell back from [" + primary + "] to [" + fb + "]");
                return fbLoc;
            }
        }

        // If nothing works, return primary (so Playwright throws a good error)
        System.out.println("[SELF-HEAL] '" + friendlyName + "' could not heal. Using primary: " + primary);
        return primaryLoc;
    }

    private Locator build(String selector) {
        // Support simple prefixes: css=, text=, role=
        if (selector.startsWith("css=")) return page.locator(selector.substring(4));
        if (selector.startsWith("text=")) return page.getByText(selector.substring(5));
        // default treat as CSS
        return page.locator(selector);
    }

    private boolean isUsable(Locator locator) {
        try {
            // Short, safe check: element must appear quickly
            locator.first().waitFor(new Locator.WaitForOptions().setTimeout(1500));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}