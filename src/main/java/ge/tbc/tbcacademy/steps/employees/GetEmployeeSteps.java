package ge.tbc.tbcacademy.steps.employees;

import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.GetEmployeeByIdRequest;
import com.example.springboot.soap.interfaces.GetEmployeeByIdResponse;
import com.example.springboot.soap.interfaces.ObjectFactory;
import ge.tbc.tbcacademy.utils.Marshall;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;

import static ge.tbc.tbcacademy.data.EmployeesConstants.*;
import static ge.tbc.tbcacademy.data.specBuilders.RequestSpecs.getEmployeeRequestSpec;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetEmployeeSteps {
    String requestBody;
    EmployeeInfo employeeInfo;
    Response response;
    SOAPFault soapFault = null;

    @Step("Create the request body for getting an employee with ID {0}")
    public GetEmployeeSteps createGetEmployeeRequestBody(int id){
        ObjectFactory factory = new ObjectFactory();
        GetEmployeeByIdRequest getEmployeeByIdRequest = factory
                .createGetEmployeeByIdRequest()
                .withEmployeeId(id);
        requestBody = Marshall.marshallSoapRequest(getEmployeeByIdRequest);
        return this;
    }

    @Step("Send the get employee request and print the response")
    public GetEmployeeSteps getEmployeeById(){
        response = getEmployeeRequestSpec
                .body(requestBody)
                .post(EMPLOYEES_API_LINK);
        try {
            employeeInfo = Marshall.unmarshallResponse(response.asString(), GetEmployeeByIdResponse.class).getEmployeeInfo();
        }catch (SOAPFaultException e){
            soapFault = e.getFault();
        }
        return this;
    }

    @Step("Validate the response contains employee id '{0}'")
    public GetEmployeeSteps validateEmployeeId(int id){
        assertThat(employeeInfo.getEmployeeId(), equalTo((long) id));
        return this;
    }

    @Step("Validate the response contains employee name '{0}'")
    public GetEmployeeSteps validateEmployeeName(String name){
        assertThat(employeeInfo.getName(), equalTo(name));
        return this;
    }

    @Step("Validate the response contains employee department '{0}'")
    public GetEmployeeSteps validateEmployeeDepartment(String department){
        assertThat(employeeInfo.getDepartment(), equalTo(department));
        return this;
    }

    @Step("Validate the response contains employee phone '{0}'")
    public GetEmployeeSteps validateEmployeePhone(String phone){
        assertThat(employeeInfo.getPhone(), equalTo(phone));
        return this;
    }

    @Step("Validate the response contains employee address '{0}'")
    public GetEmployeeSteps validateEmployeeAddress(String address){
        assertThat(employeeInfo.getAddress(), equalTo(address));
        return this;
    }

    @Step("Validate the response contains employee salary '{0}'")
    public GetEmployeeSteps validateEmployeeSalary(int salary){
        assertThat(employeeInfo.getSalary().toString(), equalTo(String.format("%.2f", (float) salary)));
        return this;
    }

    @Step("Validate the response contains employee email '{0}'")
    public GetEmployeeSteps validateEmployeeEmail(String email){
        assertThat(employeeInfo.getEmail(), equalTo(email));
        return this;
    }

    @Step("Validate the response contains employee birth date '{0}'")
    public void validateEmployeeBirthDate(String date){
        assertThat(employeeInfo.getBirthDate().toString(), equalTo(date));
    }

    @Step("Validate the response status code is 500")
    public GetEmployeeSteps validateResponseStatusCodeIsInternalServerError(){
        response
                .then()
                .assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
        return this;
    }


    @Step("Validate the response contains error message 'Source must not be null'")
    public void validateResponseMessage(){
        if (soapFault != null){
            assertThat(soapFault.getFaultString(), equalTo(SOURCE_NOT_NULL_MESSAGE));
        }
    }

}
