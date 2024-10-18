package printer;

import accounts.Account;
import org.poo.cb.Builder;
import stocks.Stocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class PortfolioFormatBuilder implements Builder {

    ArrayList<Stocks> stocks;
    ArrayList<AccountPrintFormat> accounts;
    public PortfolioFormatBuilder() {
        this.accounts = new ArrayList<>();
    }
    @Override
    public void email(String email) {

    }

    @Override
    public void firstName(String firstName) {

    }

    @Override
    public void lastName(String lastName) {

    }

    @Override
    public void address(String address) {

    }

    @Override
    public void friends(ArrayList<String> friends) {

    }

    @Override
    public void stocks(ArrayList<Stocks> stocks) {
        this.stocks = stocks;
    }

    @Override
    public void accounts(ArrayList<Account> accounts) {
        for (Account i: accounts) {
            this.accounts.add(new AccountPrintFormat(i.getCurrency(), i.getAmount()));
        }
    }

    public PortfolioPrinterFormat getResult() {
        return new PortfolioPrinterFormat(this.stocks, this.accounts);
    }
}