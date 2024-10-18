package user;

import accounts.Account;
import accounts.AccountFactory;
import stocks.Stocks;
import stocks.Stocks;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String email;
    private String lastName;
    private String firstName;
    private String address;
    private ArrayList<String> friends;
    private ArrayList<Account> accounts;
    private ArrayList<Stocks> stocks;
    private boolean premium;

    User(String email, String lastName, String firstName, String address) {
        this.friends = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.stocks = new ArrayList<>();
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.premium = false;
    }

    public boolean getPremium() {
        return this.premium;
    }
    public String getEmail() {
        return this.email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Stocks> getStocks() {
        return this.stocks;
    }
    public void addFriend(String friend) {
//        check if friend already exist
        for (String i : this.friends) {
            if (friend.equals(i)) {
                System.out.println("User with " + friend + " is already a friend");
                return;
            }
        }
//            add new friend
        this.friends.add(friend);
    }

    public void addAccount(String currency) {
//        check if account already exist
        for (Account i: this.accounts) {
            if (currency.equals(i.getCurrency())) {
                System.out.println("Account in currency " + currency + " already exists for user");
                return;
            }
        }
//            add new account
        AccountFactory factory = AccountFactory.getInstance();
        this.accounts.add(factory.createAccount(currency));
    }

    public void transferMoney(User friend, String currency, double amount) {
//        check it they are friends
        boolean ok = false;
        for (String i: this.friends) {
            if (i.equals(friend.getEmail())) {
                ok = true;
            }
        }
        if (ok == false) {
            System.out.println("You are not allowed to transfer money to " + friend.getEmail());
            return;
        }
//        get the account
        Account currentAccount = null;
        for (Account i: this.accounts) {
            if (i.getCurrency().equals(currency)) {
                currentAccount = i;
                break;
            }
        }
//        check if the money is enough
        if (currentAccount.getAmount() < amount) {
            System.out.println("Insufficient amount in account " + currentAccount.getCurrency() + " for transfer");
            return;
        }
//        drop money
        currentAccount.dropMoney(amount);
//        add money to friend
        friend.receiveMoney(currency, amount);
    }

    private void receiveMoney(String currency, double amount) {
//        get account
        Account currentAccount = null;
        for (Account i: this.accounts) {
            if (i.getCurrency().equals(currency)) {
                currentAccount = i;
                break;
            }
        }
        currentAccount.addMoney(amount);
    }

    public void buyStocks(String name, double priceToPay, int amount) {
//        check if is enough money
        Account usd = null;
        for (Account i: this.accounts) {
            if (i.getCurrency().equals("USD")) {
                usd = i;
                break;
            }
        }
        if (usd == null || usd.getAmount() < priceToPay) {
            System.out.println("Insufficient amount in account for buying stock");
            return;
        }
//        search for company
        Stocks currentCompany = null;
        for (Stocks i: this.stocks) {
            if (i.getStockName().equals(name)) {
                currentCompany = i;
                break;
            }
        }
        if (currentCompany == null) {
//            new company
            Stocks newCompany = new Stocks(name);
            newCompany.setAmount(amount);
            this.stocks.add(newCompany);
        } else {
//            already existing company
            currentCompany.addAmount(amount);
        }
//        drop money
        usd.dropMoney(priceToPay);
    }

    public void buyPremium() {
        for (Account i: this.accounts) {
            if (i.getCurrency().equals("USD")) {
                i.dropMoney(100);
                break;
            }
        }
        this.premium = true;
    }
}