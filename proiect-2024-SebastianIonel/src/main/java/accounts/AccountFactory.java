package accounts;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AccountFactory {
    private static AccountFactory instance;

    private AccountFactory() {
    }

    public static AccountFactory getInstance() {
        if (instance == null) {
            instance = new AccountFactory();
        }
        return instance;
    }

    public Account createAccount(String currency) {
        try {
            Class<?> c = Class.forName("accounts." + currency);
            Constructor<?> constructor = c.getDeclaredConstructor();
            return (Account) constructor.newInstance();
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.out.println("This shouldn't happen. Check accounts/AccountFactory line 22: " + e);
        }
        return null;
    }
}