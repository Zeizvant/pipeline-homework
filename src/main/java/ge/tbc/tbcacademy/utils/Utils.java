package ge.tbc.tbcacademy.utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ge.tbc.tbcacademy.data.Constants.VERTICAL_CENTER_OPTION;

public class Utils {

    /**
     * Finds a table row (<tr>) within a table body that contains the specified text.
     *
     * @param tableBody The SelenideElement representing the table body (<tbody>).
     * @param text The text to search for within the table row.
     * @return An ElementsCollection representing all the cells (<td>) within the found row.
     */
    public static ElementsCollection getRowContainingText(SelenideElement tableBody, String text) {
        SelenideElement targetRow = tableBody
                .$(byText(text))
                .scrollIntoView(VERTICAL_CENTER_OPTION)
                .closest("tr");
        return targetRow.$$(byTagName("td"));
    }

    /**
     * Searches for the index of the header containing the specified offer name.
     *
     * @param headers   A collection of header elements (e.g., <th>)
     * @param offerName The name of the offer to search for
     * @return The index of the header containing the offer name, or -1 if not found
     */
    public static int findOfferIndex(ElementsCollection headers, String offerName) {
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().contains(offerName)) {
                return i;
            }
        }
        return -1;
    }

    public static int findHeaderIndex(ElementsCollection headers, String header){
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().equals(header)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Fills a personal data form with provided information.
     *
     * @param firstName The first name to enter in the form.
     * @param lastName The last name to enter in the form.
     * @param gender The gender to select in the form (must be "male", "female", or other supported value).
     * @param mobile The mobile number to enter in the form.
     */
    public static void fillPersonalDataForm(String firstName, String lastName, String gender, String mobile){
        $("#firstName").sendKeys(firstName);
        $("#lastName").sendKeys(lastName);
        if(gender.equalsIgnoreCase("male")){
            $("#gender-radio-1").scrollIntoView(VERTICAL_CENTER_OPTION).parent().click();
        }else if(gender.equalsIgnoreCase("female")){
            $("#gender-radio-2").scrollIntoView(VERTICAL_CENTER_OPTION).parent().click();
        }else {
            $("#gender-radio-3").scrollIntoView(VERTICAL_CENTER_OPTION).parent().click();
        }
        $("#userNumber").scrollIntoView(VERTICAL_CENTER_OPTION).sendKeys(mobile);
    }
}
