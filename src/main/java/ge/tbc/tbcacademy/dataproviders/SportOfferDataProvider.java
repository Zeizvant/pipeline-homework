package ge.tbc.tbcacademy.dataproviders;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Arrays;

import static ge.tbc.tbcacademy.data.Constants.offersData;

public class SportOfferDataProvider {
    @DataProvider(name="sportOfferDataProvider")
    public static Object[][] sportOffersData(Method method){
        if (method.getName().equals("checkSaleValuesTest")){
            return offersData;
        }else if (method.getName().equals("validateCartBehavior")){
            // return only title and description
            return Arrays.stream(offersData)
                    .map(offerData -> Arrays.copyOf(offerData, 2))
                    .toArray(Object[][]::new);
        }
        return new Object[][] {};
    }
}
