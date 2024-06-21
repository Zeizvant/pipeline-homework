package ge.tbc.tbcacademy.rest;

import ge.tbc.tbcacademy.steps.soapDemo.FindPersonSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.tbcacademy.data.SoapDemoConstants.*;

@Epic("API Integration Tests")
public class SoapDemoTests {
    FindPersonSteps findPersonSteps;

    @BeforeClass
    public void setUp(){
        findPersonSteps = new FindPersonSteps();
    }

    @Test(description = "Test case to find a person by ID and validate various details in the response")
    @Description("This test case verifies the end-to-end functionality of finding a person by their ID using a SOAP API request. "
            + "It performs comprehensive validation of the retrieved person's information")
    @Feature("Find Person Functionality")
    @Story("End-to-End Find Person Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void findPersonTests(){
        findPersonSteps
                .createFindPersonRequestWithId(ID_VALUE)
                .sendRequest()
                .validatePresenceOfResponseElement()
                .validateNameOfPerson(NAME_VALUE)
                .validateSSNOfPerson(SSN_VALUE)
                .validateNumberOfCharactersInSSN(NUM_OF_CHARS)
                .validateSSNPattern()
                .validatePersonsDateOfBirth(BIRTH_DATE_VALUE)
                .validateFavouriteColor(ORANGE_COLOR_VALUE)
                .validateFavouriteColor(RED_COLOR_VALUE)
                .validateLastNameOfPerson(LAST_NAME_VALUE)
                .validateOfficeAndHomeZipCodesAreDifferent();
    }
}
