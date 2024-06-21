package ge.tbc.tbcacademy.utils;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceCalculator {
    public static float termPrice = 849;
    private float basePrice;
    private int licensesQuantity = 1;
    private int supportQuantity;
    private float subtotal;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    DecimalFormat df = new DecimalFormat("#.##");

    // my calculator
    // calculator helper methods
    public PriceCalculator(float basePrice, int licensesQuantity, int supportQuantity){
        this.licensesQuantity = licensesQuantity;
        this.supportQuantity = supportQuantity;
        this.basePrice = basePrice;
        calculateSubTotal();
    }

    public String getSubtotal() {
        return formatter.format(Double.parseDouble(df.format(subtotal)));
    }
    public void setLicensesQuantity(int licensesQuantity) {
        this.licensesQuantity = licensesQuantity;
        calculateSubTotal();
    }


    /**
     * Calculates the license discount percentage based on the quantity of licenses.
     *
     * @return The license discount percentage as a float value, or -1.0f if there is no discount.
     */
    private float calculateLicensesPercent(){
        float[] discountTable = { -1, 0.05F, 0.1F };

        int index = Math.min(Math.max(0, licensesQuantity - 1), discountTable.length - 1);
        return discountTable[index];
    }

    /**
     * Calculates the subtotal cost.
     */
    private void calculateSubTotal() {
        float licensesDiscount = calculateLicensesPercent() == -1 ? 0 : calculateLicensesPercent();
        float supportDiscount = calculateSupportPercent() == -1 ? 0 : calculateSupportPercent();

        float licensesCost = licensesQuantity * basePrice * (1 - licensesDiscount);
        float supportCost = licensesQuantity * supportQuantity * termPrice * (1 - supportDiscount);

        this.subtotal = licensesDiscount == 0 && supportDiscount == 0
                ? basePrice // Neither discount applied
                : supportDiscount == 0
                ? licensesCost // Only licenses discount
                : licensesCost + supportCost; // Both discounts applied
    }

    /**
     * Calculates and returns the term price.
     *
     * @return A formatted string representing the term price.
     */
    public String getTermPrice(){
        float supportDiscount = calculateSupportPercent() == -1 ? 0 : calculateSupportPercent();
        float price = licensesQuantity * termPrice * (1 - supportDiscount);
        return formatter.format(Double.parseDouble(df.format(price)));
    }

    /**
     * Calculates and returns the total discount amount for licenses.
     *
     * @return A formatted string representing the total license discount.
     */
    public String getLicensesTotalDiscount(){
        float licensesDiscount = calculateLicensesPercent() == -1 ? 0 : calculateLicensesPercent();
        return formatter.format(Double.parseDouble(df.format(basePrice * licensesQuantity * licensesDiscount)));
    }

    /**
     * Calculates the total support discount.
     *
     * @return A formatted string representing the total support discount.
     */
    public String getSupportTotalDiscount(){
        float supportDiscount = calculateSupportPercent() == -1 ? 0 : calculateSupportPercent();
        return formatter.format(Double.parseDouble(df.format(licensesQuantity * supportQuantity * termPrice * supportDiscount)));
    }

    /**
     * Calculates and returns the unit price savings.
     *
     * @return A formatted string representing the unit saving.
     */
    public String getUnitSave(){
        float licensesDiscount = calculateLicensesPercent() == -1 ? 0 : calculateLicensesPercent();
        return formatter.format(Double.parseDouble(df.format(basePrice * licensesDiscount)));
    }

    /**
     * Calculates the support percentage discount based on the number of licenses and support quantity.
     *
     * @return The calculated discount percentage as a float value.
     */
    private float calculateSupportPercent() {
        // two dimensional array for percents
        float[][] discountTable = {
                {0.05F, 0.08F, 0.11F, 0.14F}, // licensesQuantity = 1
                {0.08F, 0.11F, 0.14F, 0.17F}, // 1 < licensesQuantity < 6
                {0.13F, 0.16F, 0.19F, 0.22F}  // licensesQuantity >= 6
        };
        // prevent errors determining borders
        int licenseIndex = Math.min(Math.max(0, licensesQuantity - 1), discountTable.length - 1);
        int supportIndex = Math.min(Math.max(0, supportQuantity - 1), discountTable[0].length - 1);

        return discountTable[licenseIndex][supportIndex];
    }
}
