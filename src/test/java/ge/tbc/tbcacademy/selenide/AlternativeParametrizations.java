package ge.tbc.tbcacademy.selenide;

import static com.codeborne.selenide.Condition.exactValue;
import static ge.tbc.tbcacademy.data.Constants.*;

import ge.tbc.tbcacademy.config.ConfigTests;
import ge.tbc.tbcacademy.dataproviders.PersonalDataProvider;
import ge.tbc.tbcacademy.utils.Utils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AlternativeParametrizations extends ConfigTests {
    @BeforeMethod
    public void openAndPrepareForm(){
        open(DEMOQA_PRACTICE_FORM_PAGE);
    }

    // Implement same scenario using @DataProvider
    @Test(dataProvider = "personalDataProvider", dataProviderClass = PersonalDataProvider.class)
    public void testPersonalDataWithProvider(String firstName, String lastName, String gender, String mobile){
        Utils.fillPersonalDataForm(firstName, lastName, gender, mobile);
        $("#firstName").shouldHave(exactValue(firstName));
    }
}
