package ge.tbc.tbcacademy.steps.employees;

import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.ObjectFactory;
import com.example.springboot.soap.interfaces.UpdateEmployeeRequest;
import com.example.springboot.soap.interfaces.UpdateEmployeeResponse;
import ge.tbc.tbcacademy.utils.Marshall;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

import static ge.tbc.tbcacademy.data.EmployeesConstants.*;
import static ge.tbc.tbcacademy.data.specBuilders.RequestSpecs.updateEmployeeRequestSpec;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateEmployeeSteps {
    String requestBody;
    UpdateEmployeeResponse updateEmployeeResponse;

    @Step("Create the request body for updating employee information")
    public UpdateEmployeeSteps createUpdateEmployeeRequestBody(){
        DatatypeFactory df = null;
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        XMLGregorianCalendar xmlCalendar = df.newXMLGregorianCalendar(DATE_UPDATE_VALUE);
        ObjectFactory factory = new ObjectFactory();
        UpdateEmployeeRequest updateEmployeeRequest = factory.createUpdateEmployeeRequest();
        EmployeeInfo employeeInfo = factory
                .createEmployeeInfo()
                .withEmployeeId(ID)
                .withAddress(ADDRESS_UPDATE_VALUE)
                .withName(NAME_UPDATE_VALUE)
                .withDepartment(DEPARTMENT_UPDATE_VALUE)
                .withSalary(BigDecimal.valueOf(SALARY_UPDATE_VALUE))
                .withBirthDate(xmlCalendar)
                .withEmail(EMAIL_UPDATE_VALUE);
        updateEmployeeRequest.withEmployeeInfo(employeeInfo);
        requestBody = Marshall.marshallSoapRequest(updateEmployeeRequest);
        return this;
    }

    @Step("Send the update employee request")
    public UpdateEmployeeSteps sendEmployeeUpdateRequest(){
        Response response = updateEmployeeRequestSpec
                .body(requestBody)
                .post(EMPLOYEES_API_LINK);
        updateEmployeeResponse = Marshall.unmarshallResponse(response.asString(), UpdateEmployeeResponse.class);
        return this;
    }

    @Step("Validate the response message indicates successful update")
    public void validateRequestIsSuccessful(){
        assertThat(updateEmployeeResponse.getServiceStatus().getMessage(), equalTo(CONTENT_UPDATE_SUCCESS_MESSAGE));
    }
}
