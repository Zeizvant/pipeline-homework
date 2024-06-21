package ge.tbc.tbcacademy.steps.continents;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.xml.XmlPath;

import java.util.*;

import static ge.tbc.tbcacademy.data.ContinentsConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContinentsSteps {
    XmlPath responseXML;
    List<String> sNames;
    List<String> sCodes;

    @Step("Get the XML response from the continents service")
    public ContinentsSteps getResponseAsXml(){
        responseXML = given()
                .filter(new AllureRestAssured())
                .when()
                .get(WEBSERVICES_OORBSPRONG)
                .then()
                .extract()
                .response()
                .xmlPath();
        return this;
    }

    @Step("Extract the XML response and store continents data")
    public ContinentsSteps extractResponseXml(){
        // add root xml
        responseXML.setRootPath("ArrayOftContinent.tContinent");
        sNames = responseXML.getList("sName");
        sCodes = responseXML.getList("sCode");
        return this;
    }

    @Step("Validate the size of the sName node list matches the expected size")
    public ContinentsSteps validateSNameNodeSize(){
        assertThat(sNames.size(), equalTo(SNAME_NODE_SIZE));
        return this;
    }

    @Step("Validating sName values")
    public ContinentsSteps validateSNameValues(){
        assertThat(sNames, containsInAnyOrder(AFRICA, ANTARCTICA, ASIA, EUROPE, OCEANIA, THE_AMERICAS));
        return this;
    }


    @Step("Validating sName value with sCode 'AN'")
    public ContinentsSteps validateSNameValueWithSCodeAN(){
        String sNameWithAN = responseXML.getString("find { it.sCode == 'AN' }.sName");
        assertThat(sNameWithAN, equalTo(ANTARCTICA));
        return this;
    }

    @Step("Validating last continent sName")
    public ContinentsSteps validateLastContinentSName(){
        assertThat(responseXML. getString("last().sName"), equalTo(THE_AMERICAS));
        return this;
    }

    @Step("Validating each sName is unique")
    public ContinentsSteps validateEachSNameIsUnique(){
        Set<String> uniqueSNames = new HashSet<>(sNames);
        assertThat(uniqueSNames.size(), equalTo(sNames.size()));
        return this;
    }

    @Step("Validating presence of sName values")
    public ContinentsSteps validatePresenceOfSNameValues(){
        assertThat(sNames, everyItem(not(emptyOrNullString())));
        return this;
    }

    @Step("Validating presence of sCode values")
    public ContinentsSteps validatePresenceOfSCodeValues(){
        assertThat(sCodes, everyItem(not(emptyOrNullString())));
        return this;
    }

    @Step("Validating correctness of values")
    public ContinentsSteps validateCorrectnessOfValues(){
        for (int i = 0; i < sCodes.size(); i++) {
            String sCode = sCodes.get(i).toLowerCase();
            String sName = sNames.get(i).toLowerCase();
            assertThat(sName, containsString(sCode));
        }
        return this;
    }

    @Step("Validating sCode pattern")
    public ContinentsSteps validateSCodePattern(){
        assertThat(sCodes, everyItem(matchesPattern("[A-Z]{2}")));
        return this;
    }

    @Step("Validating sName alphabetical order")
    public ContinentsSteps validateSNameAlphabeticalOrder(){
        List<String> sortedSNames = new ArrayList<>(sNames);
        Collections.sort(sortedSNames);
        assertThat(sNames, equalTo(sortedSNames));
        return this;
    }

    @Step("Validating presence of all six continents")
    public ContinentsSteps validatePresenceOfAllSixContinents(){
        assertThat(responseXML.getList(""), everyItem(not(empty())));
        return this;
    }

    @Step("Validating sName doesn't contain numbers")
    public ContinentsSteps validateSNameDontContainsNumbers(){
        assertThat(sNames, everyItem(not(matchesPattern("//d"))));
        return this;
    }

    @Step("Validating sCode starts with 'O'")
    public ContinentsSteps validateSCodeStartsWithO(){
        String sCodeWithO = responseXML.getString("find { it.sCode.text().startsWith('O') }.sName");
        assertThat(sCodeWithO, equalTo(OCEANIA));
        return this;
    }

    @Step("Validating sName pattern and numeric value")
    public void validateSNamePatternAndNumericValue(){
        int size = responseXML.getList("findAll { it.sName.text().startsWith('A') && it.sName.text().endsWith('ca') && it.sName !=~ /\\d/ }").size();
        assertThat(size, equalTo(MATCHED_SNAME_NODE_SIZE));
    }
}
