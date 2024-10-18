package org.poo.cb;

import user.User;

// singleton, it will be used in many functions
public class Director {
    private static Director instance;

    private Director() {
    }

    public static Director getInstance() {
        if (instance == null) {
            instance = new Director();
        }
        return instance;
    }

    public void makeUser(Builder builder, String[] content) {
        builder.email(content[2]);
        builder.firstName(content[3]);
        builder.lastName(content[4]);
        String address = "";
        for (int i = 5; i < content.length; i++) {
            if (i == content.length - 1){
                address += content[i];
            } else {
                address += content[i] + " ";
            }
        }
        builder.address(address);
    }

    public void makeUserFormat(Builder builder, User user) {
        builder.email(user.getEmail());
        builder.lastName(user.getLastName());
        builder.firstName(user.getFirstName());
        builder.address(user.getAddress());
        builder.friends(user.getFriends());
    }

    public void makePortfolioFormat(Builder builder, User user) {
        builder.stocks(user.getStocks());
        builder.accounts(user.getAccounts());
    }
}