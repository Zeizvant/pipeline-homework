package ge.tbc.tbcacademy.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

import static ge.tbc.tbcacademy.data.Constants.*;

// my custom condition
// HasDescendantAnchorWithHref custom condition
/**
 * Custom Selenide condition to check if a WebElement has a descendant anchor tag with href containing a specific value.
 */
public class HasDescendantAnchorWithHref extends WebElementCondition {
    private final String value;

    public HasDescendantAnchorWithHref(String value) {
        super(String.format(ANCHOR_TAG_CONTAINS_PATTERN, value));
        this.value = value;
    }

    /**
     * Checks if the given WebElement has a descendant anchor tag with href containing the specified value.
     *
     * @param driver     The Selenide Driver
     * @param webElement The WebElement to check
     * @return a CheckResult indicating whether the condition is met or not
     */
    @Nonnull
    @Override
    public CheckResult check(Driver driver, WebElement webElement) {
        if (webElement.findElements(By.cssSelector(String.format("a[href*='%s']", value))).isEmpty()){
            return CheckResult.rejected(String.format(NO_ANCHOR_WITH_HREF_PATTERN, value), webElement);
        }
        return CheckResult.accepted(String.format(ANCHOR_HREF_MATCH_FOUND, value));
    }
}