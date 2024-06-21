package ge.tbc.tbcacademy.selenide;

import ge.tbc.tbcacademy.config.ConfigTests;

import static com.codeborne.selenide.Condition.exactText;
import static ge.tbc.tbcacademy.data.Constants.*;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;

public class DependsOnTest extends ConfigTests {
    // comment on issues DependsOnTest
    // DependsOnTest on testNGParametrization branch
    @Test
    public void searchTest(){
        open(SWOOP_WEBPAGE);
        $(".search-box > input").sendKeys(INPUT_SEARCHABLE_WORD);
        $$(".search-result")
                .shouldHave(sizeGreaterThan(0))
                .should(allMatch(ALL_CONTAINS_TEXT, element -> executeJavaScript(GET_TEXT_CONTENT_JS, element)
                        .toString()
                        .contains(INPUT_SEARCHABLE_WORD)));
    }

    @Test(dependsOnMethods = "searchTest", alwaysRun = true)
    public void validateIndividualOfferNameFromSearch(){
        String title = $(".search-result__title").getText();
        $(".search-result").click();
        $(".merchantTitle").shouldHave(exactText(title));
    }
}
