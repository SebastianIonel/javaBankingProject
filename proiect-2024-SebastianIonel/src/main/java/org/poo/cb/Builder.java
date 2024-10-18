package org.poo.cb;

import accounts.Account;
import stocks.Stocks;

import java.util.ArrayList;
import java.util.HashMap;

public interface Builder {
    public void email(String email);

    public void firstName(String firstName);

    public void lastName(String lastName);

    public void address(String address);

    public void friends(ArrayList<String> friends);
    public void stocks(ArrayList<Stocks> stocks);
    public void accounts(ArrayList<Account> accounts);
    public <T> T getResult();
}