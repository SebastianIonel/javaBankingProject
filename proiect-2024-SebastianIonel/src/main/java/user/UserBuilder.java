package user;
import accounts.Account;
import org.poo.cb.Builder;
import stocks.Stocks;

import java.util.ArrayList;
import java.util.HashMap;

public class UserBuilder implements Builder {
    private String email;
    private String lastName;
    private String firstName;
    private String address;

    @Override
    public void email(String email) {
        this.email = email;
    }

    @Override
    public void firstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void lastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void address(String address) {
        this.address = address;
    }

    @Override
    public void friends(ArrayList<String> friends) {}

    @Override
    public void stocks(ArrayList<Stocks> stocks) {}

    @Override
    public void accounts(ArrayList<Account> accounts) {}

    public User getResult() {
        return new User(this.email, this.lastName, this.firstName, this.address);
    }

}