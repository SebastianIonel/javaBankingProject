package printer;

import accounts.Account;
import org.poo.cb.Builder;
import stocks.Stocks;

import java.util.ArrayList;
import java.util.HashMap;

public class UserFormatBuilder implements Builder {

    private String email;
    private String firstname;
    private String lastname;
    private String address;
    private ArrayList<String> friends;
    @Override
    public void email(String email) {
        this.email = email;
    }

    @Override
    public void firstName(String firstName) {
        this.firstname = firstName;
    }

    @Override
    public void lastName(String lastName) {
        this.lastname = lastName;
    }

    @Override
    public void address(String address) {
        this.address = address;
    }

    @Override
    public void friends(ArrayList<String> friends) {
        this.friends = friends;
    }

    @Override
    public void stocks(ArrayList<Stocks> stocks) {}

    @Override
    public void accounts(ArrayList<Account> accounts) {}

    public UserPrinterFormat getResult() {
        return new UserPrinterFormat(this.email, this.firstname, this.lastname, this.address, this.friends);
    }
}