package ge.tbc.tbcacademy.steps.employees;

import com.example.springboot.soap.interfaces.DeleteEmployeeRequest;
import com.example.springboot.soap.interfaces.DeleteEmployeeResponse;
import com.example.springboot.soap.interfaces.ObjectFactory;
import ge.tbc.tbcacademy.utils.Marshall;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static ge.tbc.tbcacademy.data.EmployeesConstants.*;
import static ge.tbc.tbcacademy.data.specBuilders.RequestSpecs.deleteEmployeeRequestSpec;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteEmployeeSteps {
    String requestBody;
    DeleteEmployeeResponse deleteEmployeeResponse;

    @Step("Create the request body for deleting an employee")
    public DeleteEmployeeSteps createDeleteEmployeeRequestBody(){
        ObjectFactory factory = new ObjectFactory();
        DeleteEmployeeRequest deleteEmployeeRequest = factory
                .createDeleteEmployeeRequest()
                .withEmail(EMAIL_VALUE)
                .withEmployeeId((long) ID);
        requestBody = Marshall.marshallSoapRequest(deleteEmployeeRequest);
        return this;
    }

    @Step("Send the delete employee request")
    public DeleteEmployeeSteps sendDeleteRequest(){
        Response response = deleteEmployeeRequestSpec
                .body(requestBody)
                .post(EMPLOYEES_API_LINK);
        deleteEmployeeResponse = Marshall.unmarshallResponse(response.asString(), DeleteEmployeeResponse.class);
        return this;
    }

    @Step("Validate the response message indicates successful deletion")
    public void validateDeleteResponseMessage(){
        assertThat(deleteEmployeeResponse.getServiceStatus().getMessage(), equalTo(CONTENT_DELETE_MESSAGE));
    }
}
