package healing;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HealBy {
    String primary();        // e.g. "css=#user-name"
    String[] fallbacks() default {}; // e.g. {"css=input[name='user-name']", "text=Username"}
    String friendlyName() default "";
}
