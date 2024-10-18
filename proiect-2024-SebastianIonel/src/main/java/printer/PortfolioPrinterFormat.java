package printer;

import accounts.Account;
import stocks.Stocks;

import java.util.ArrayList;

public class PortfolioPrinterFormat {
    private ArrayList<Stocks> stocks;
    private ArrayList<AccountPrintFormat> accounts;

    public PortfolioPrinterFormat(ArrayList<Stocks> stocks, ArrayList<AccountPrintFormat> accounts) {
        this.stocks = stocks;
        this.accounts = accounts;
    }

    public ArrayList<Stocks> getStocks() {
        return stocks;
    }

    public ArrayList<AccountPrintFormat> getAccounts() {
        return accounts;
    }
}