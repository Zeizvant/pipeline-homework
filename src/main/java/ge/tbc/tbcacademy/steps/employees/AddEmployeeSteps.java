package ge.tbc.tbcacademy.steps.employees;

import com.example.springboot.soap.interfaces.AddEmployeeRequest;
import com.example.springboot.soap.interfaces.AddEmployeeResponse;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.ObjectFactory;
import ge.tbc.tbcacademy.utils.Marshall;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

import static ge.tbc.tbcacademy.data.EmployeesConstants.*;
import static ge.tbc.tbcacademy.data.specBuilders.RequestSpecs.addEmployeeRequestSpec;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddEmployeeSteps {
    AddEmployeeResponse addEmployeeResponse;
    String requestBody;

    @Step("Create the request body with employee information")
    public AddEmployeeSteps createRequestBody(){
        DatatypeFactory df = null;
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        XMLGregorianCalendar xmlCalendar = df.newXMLGregorianCalendar(DATE_VALUE);
        ObjectFactory factory = new ObjectFactory();
        AddEmployeeRequest addEmployeeRequest = factory.createAddEmployeeRequest();
        EmployeeInfo employeeInfo = factory.
                createEmployeeInfo()
                .withEmployeeId(ID)
                .withAddress(ADDRESS_VALUE)
                .withName(NAME_VALUE)
                .withDepartment(DEPARTMENT_VALUE)
                .withSalary(BigDecimal.valueOf(SALARY_VALUE))
                .withBirthDate(xmlCalendar)
                .withEmail(EMAIL_VALUE)
                .withPhone(PHONE_VALUE);

        addEmployeeRequest.withEmployeeInfo(employeeInfo);
        requestBody = Marshall.marshallSoapRequest(addEmployeeRequest);
        return this;
    }

    @Step("Send the add employee request")
    public AddEmployeeSteps addEmployee(){
        Response response = addEmployeeRequestSpec
                .body(requestBody)
                .post(EMPLOYEES_API_LINK);
        addEmployeeResponse = Marshall.unmarshallResponse(response.asString(), AddEmployeeResponse.class);
        return this;
    }

    @Step("Validate the response message indicates successful addition")
    public void validateRequestMessage(){
        assertThat(addEmployeeResponse.getServiceStatus().getMessage(), equalTo(CONTENT_ADD_SUCCESS_MESSAGE));
    }
}
