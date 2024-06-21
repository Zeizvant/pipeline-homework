package ge.tbc.tbcacademy.data.specBuilders;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecs {
    public static RequestSpecification addEmployeeRequestSpec = given()
            .filter(new AllureRestAssured())
            .header("Content-Type", "text/xml; charset=utf-8")
            .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/addEmployeeRequest");


    public static RequestSpecification deleteEmployeeRequestSpec = given()
            .filter(new AllureRestAssured())
            .header("Content-Type", "text/xml; charset=utf-8")
            .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/deleteEmployeeRequest");

    public static RequestSpecification updateEmployeeRequestSpec = given()
            .filter(new AllureRestAssured())
            .header("Content-Type", "text/xml; charset=utf-8")
            .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/updateEmployeeRequest");

    public static RequestSpecification getEmployeeRequestSpec = given()
            .filter(new AllureRestAssured())
            .header("Content-Type", "text/xml; charset=utf-8")
            .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/getEmployeeByIdRequest");

    public static RequestSpecification findPersonRequestSpec = given()
            .filter(new AllureRestAssured())
            .header("Content-Type", "text/xml; charset=utf-8")
            .header("SOAPAction", "http://tempuri.org/SOAP.Demo.FindPerson");
}
