package ge.tbc.tbcacademy.utils;

import com.codeborne.selenide.SelenideElement;
import ge.tbc.tbcacademy.data.Constants;

import static com.codeborne.selenide.Condition.selected;

public class FormHelper {
    //  form helper methods
    // FormHelper util
    /**
     * Unchecks a checkbox if it's currently checked.
     *
     * @param checkbox The SelenideElement representing the checkbox
     * @return The updated SelenideElement
     */
    public static SelenideElement uncheckCheckbox(SelenideElement checkbox) {
        checkbox.scrollIntoView(Constants.VERTICAL_CENTER_OPTION).shouldBe(selected).setSelected(false);
        return checkbox;
    }

    /**
     * Checks a checkbox if it's currently unchecked.
     *
     * @param checkbox The SelenideElement representing the checkbox
     * @return The updated SelenideElement
     */
    public static SelenideElement checkCheckbox(SelenideElement checkbox) {
        checkbox.scrollIntoView(Constants.VERTICAL_CENTER_OPTION).shouldNotBe(selected).setSelected(true);
        return checkbox;
    }

    /**
     * Selects an option.
     *
     * @param Option The SelenideElement representing an option
     * @return The updated SelenideElement
     */
    public static SelenideElement selectOption(SelenideElement Option) {
        Option.scrollIntoView(Constants.VERTICAL_CENTER_OPTION).click();
        return Option;
    }
}
