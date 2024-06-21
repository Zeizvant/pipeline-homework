package ge.tbc.tbcacademy.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

import static ge.tbc.tbcacademy.data.Constants.*;

public class HasDescendantTitleAndDescription extends WebElementCondition {
    private final String title;
    private final String description;
    public HasDescendantTitleAndDescription(String title, String description) {
        super(String.format(ELEMENT_CONTAINS_PATTERN, title, description));
        this.title = title;
        this.description = description;
    }

    /**
     * Checks if a web element contains a link with the specified description and a paragraph with the specified title.
     *
     * @param driver The WebDriver instance.
     * @param webElement The WebElement to be checked.
     * @return A CheckResult object indicating whether the element contains the specified link and paragraph.
     */
    @Nonnull
    @Override
    public CheckResult check(Driver driver, WebElement webElement) {
        if(!webElement.findElements(By.xpath(String.format(".//a[normalize-space()='%s']", description))).isEmpty() &&
                !webElement.findElements(By.xpath(String.format(".//p[text()='%s']", title))).isEmpty()){
            return CheckResult.accepted(String.format(ELEMENT_FOUND_WITH_TITLE_AND_DESCRIPTION, title, description));
        }
        return CheckResult.rejected(String.format(NO_ELEMENT_WITH_TITLE_AND_DESCRIPTION, title,description), webElement);
    }
}
