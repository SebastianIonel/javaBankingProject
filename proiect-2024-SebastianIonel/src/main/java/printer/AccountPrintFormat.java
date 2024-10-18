package printer;

import java.text.DecimalFormat;

public class AccountPrintFormat {
    private String currencyName;
    private String amount;
    public AccountPrintFormat(String currencyName, double amount) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.amount = df.format(amount);
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getAmount() {
        return amount;
    }
}