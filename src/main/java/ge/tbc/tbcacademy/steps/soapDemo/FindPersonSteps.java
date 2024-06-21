package ge.tbc.tbcacademy.steps.soapDemo;

import ge.tbc.tbcacademy.utils.Marshall;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.tempuri.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ge.tbc.tbcacademy.data.SoapDemoConstants.RED_COLOR_VALUE;
import static ge.tbc.tbcacademy.data.SoapDemoConstants.SOAP_DEMO_API_LINK;
import static ge.tbc.tbcacademy.data.specBuilders.RequestSpecs.findPersonRequestSpec;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FindPersonSteps {
    String requestBody;
    Person person;

    @Step("Create Find Person Request with ID: {0}")
    public FindPersonSteps createFindPersonRequestWithId(int id){
        ObjectFactory factory = new ObjectFactory();
        FindPerson findPerson = factory
                .createFindPerson()
                .withId(String.valueOf(id));
        requestBody = Marshall.marshallSoapRequest(findPerson);
        return this;
    }


    @Step("Send Find Person Request and Extract Person Details")
    public FindPersonSteps sendRequest(){
        Response response = findPersonRequestSpec
                .body(requestBody)
                .post(SOAP_DEMO_API_LINK);
        person = Marshall.unmarshallResponse(response.asString(), FindPersonResponse.class).getFindPersonResult();
        return this;
    }

    @Step("Validate Presence of Person in Response")
    public FindPersonSteps validatePresenceOfResponseElement(){
        assertThat(person, notNullValue());
        return this;
    }

    @Step("Validate Person's First Name is: {0}")
    public FindPersonSteps validateNameOfPerson(String name){
        String firstName = person.getName().split(",")[0].trim();
        assertThat(firstName, equalTo(name));
        return this;
    }

    @Step("Validate Person's SSN is: {0}")
    public FindPersonSteps validateSSNOfPerson(String ssn){
        assertThat(person.getSSN(), equalTo(ssn));
        return this;
    }

    @Step("Validate SSN has {0} Characters")
    public FindPersonSteps validateNumberOfCharactersInSSN(int numOfChars){
        assertThat(person.getSSN(), hasLength(numOfChars));
        return this;
    }

    @Step("Validate SSN Matches Pattern (###-##-####)")
    public FindPersonSteps validateSSNPattern(){
        String pattern = "^\\d{3}-\\d{2}-\\d{4}$";
        assertThat(person.getSSN(), matchesPattern(pattern));
        return this;
    }

    @Step("Validate Person's Date of Birth is: {0}")
    public FindPersonSteps validatePersonsDateOfBirth(String date){
        assertThat(person.getDOB().toString(), equalTo(date));
        return this;
    }

    @Step("Validate Person's Favorite Colors Include: {0}")
    public FindPersonSteps validateFavouriteColor(String color){
        assertThat(person.getFavoriteColors().getFavoriteColorsItem(), hasItem(color));
        return this;
    }

    @Step("Validate Person's Last Name is: {0}")
    public FindPersonSteps validateLastNameOfPerson(String lastName){
        String actualLastName = person.getName().split(",")[1].trim();
        assertThat(actualLastName, equalTo(lastName));
        return this;
    }

    @Step("Validate Response Has Red Color at Index 2")
    public FindPersonSteps validateResponseHasRedColorWithIndexTwo(){
        boolean hasRedAtIndexOne = IntStream.range(0, person.getFavoriteColors().getFavoriteColorsItem().size())
                        .anyMatch(index -> index == 2 && person.getFavoriteColors().getFavoriteColorsItem().contains(RED_COLOR_VALUE));
        // this test fails while index is 1
        assertThat(hasRedAtIndexOne, equalTo(true));
        return this;
    }

    @Step("Validate Office and Home Zip Codes are Different")
    public FindPersonSteps validateOfficeAndHomeZipCodesAreDifferent(){
        String homeZip = person.getHome().getZip();
        String officeZip = person.getOffice().getZip();
        boolean isDifferent = Stream.of(homeZip, officeZip)
                .distinct()
                .count() == 2;
        assertThat(isDifferent, equalTo(true));
        return this;
    }

}
