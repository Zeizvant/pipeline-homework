package ge.tbc.tbcacademy.dataproviders;

import org.testng.annotations.DataProvider;

import static ge.tbc.tbcacademy.data.Constants.personalData;

public class PersonalDataProvider {
    @DataProvider(name="personalDataProvider")
    public static Object[][] personalData(){
        return personalData;
    }
}
