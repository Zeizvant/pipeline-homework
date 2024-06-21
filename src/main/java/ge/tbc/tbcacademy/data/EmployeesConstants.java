package ge.tbc.tbcacademy.data;

public class EmployeesConstants {

    /**
     * Api links
     */
    public static final String
            EMPLOYEES_API_LINK = "http://localhost:8087/ws";


    /**
     * Constants
     */
    public static final int
            ID = 1,
            SALARY_VALUE = 10000,
            SALARY_UPDATE_VALUE = 15000;
    public static final String
            ADDRESS_VALUE = "Japaridze str N2",
            NAME_VALUE = "Zezva",
            DEPARTMENT_VALUE = "IT",
            EMAIL_VALUE = "test@gmail.com",
            PHONE_VALUE = "123",
            DATE_VALUE = "2000-04-21",
            ADDRESS_UPDATE_VALUE = "Medea Japaridze str N2",
            NAME_UPDATE_VALUE = "Zezva",
            DEPARTMENT_UPDATE_VALUE = "Sales",
            EMAIL_UPDATE_VALUE = "test1@gmail.com",
            DATE_UPDATE_VALUE = "2000-04-16";

    /**
     * Messages
     */

    public static final String
            SOURCE_NOT_NULL_MESSAGE =  "Source must not be null",
            CONTENT_UPDATE_SUCCESS_MESSAGE =  "Content Updated Successfully",
            CONTENT_DELETE_MESSAGE =  "Content Deleted Successfully",
            CONTENT_ADD_SUCCESS_MESSAGE = "Content Added Successfully",
            UNMARSHALLING_ERROR_MESSAGE = "Error unmarshalling SOAP response";
}
