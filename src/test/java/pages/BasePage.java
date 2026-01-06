package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import healing.HealBy;
import healing.HealingLocator;

import java.lang.reflect.Field;

public abstract class BasePage {
    protected final Page page;
    protected final HealingLocator healer;

    protected BasePage(Page page) {
        this.page = page;
        this.healer = new HealingLocator(page);
        // DO NOT bind here
    }

    // Call this AFTER page is loaded / navigated
    protected void bindHealingLocators() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getType() == Locator.class && f.isAnnotationPresent(HealBy.class)) {
                HealBy healBy = f.getAnnotation(HealBy.class);

                Locator resolved = healer.resolve(
                        healBy.friendlyName().isEmpty() ? f.getName() : healBy.friendlyName(),
                        healBy.primary(),
                        healBy.fallbacks()
                );

                try {
                    f.setAccessible(true);
                    f.set(this, resolved);
                } catch (IllegalAccessException ignored) {}
            }
        }
    }
}