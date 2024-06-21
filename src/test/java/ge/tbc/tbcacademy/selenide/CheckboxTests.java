package ge.tbc.tbcacademy.selenide;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Configuration;
import ge.tbc.tbcacademy.config.ConfigTests;
import ge.tbc.tbcacademy.utils.FormHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ge.tbc.tbcacademy.data.Constants.*;

@Test(groups = {"CheckBoxes-FrontEnd"})
public class CheckboxTests extends ConfigTests {
    @BeforeMethod
    public void setupClass(){
        Configuration.reportsFolder = CHECK_BOX_FAIL_TEST_REPORT_LOCATION;
        open(THE_INTERNET_CHECKBOXES_PAGE);
    }

    @Test(priority = 1, description = "uncheckCheckBox")
    public void uncheckCheckBox(){
        FormHelper.uncheckCheckbox($("#checkboxes").lastChild()).shouldNotBe(selected);

    }
}
