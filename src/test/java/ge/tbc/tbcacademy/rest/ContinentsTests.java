package ge.tbc.tbcacademy.rest;

import ge.tbc.tbcacademy.steps.continents.ContinentsSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Continents API")
public class ContinentsTests {
    ContinentsSteps continentsSteps;

    @BeforeClass
    public void setUp(){
        continentsSteps = new ContinentsSteps();
    }

    @Test(description = "Tests validations on the api content")
    @Feature("Continent Information Validation")
    @Story("Comprehensive Validation of Continent Data")
    @Description("This test performs a series of validations on the continent data retrieved from the API,"
            + " including the number of continents, their names, codes, uniqueness, format, alphabetical order,"
            + " and the presence of specific continents.")
    @Severity(SeverityLevel.CRITICAL)
    public void continentTests(){
        continentsSteps
                .getResponseAsXml()
                .extractResponseXml()
                .validateSNameNodeSize()
                .validateSNameValues()
                .validateSNameValueWithSCodeAN()
                .validateLastContinentSName()
                .validateEachSNameIsUnique()
                .validatePresenceOfSCodeValues()
                .validatePresenceOfSNameValues()
                .validateCorrectnessOfValues()
                .validateSCodePattern()
                .validateSNameAlphabeticalOrder()
                .validatePresenceOfAllSixContinents()
                .validateSNameDontContainsNumbers()
                .validateSCodeStartsWithO()
                .validateSNamePatternAndNumericValue();
    }
}
