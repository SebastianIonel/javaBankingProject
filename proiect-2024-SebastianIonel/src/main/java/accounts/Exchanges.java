package accounts;

import user.User;

import java.util.HashMap;
import java.util.Scanner;

public class Exchanges {
    private HashMap<String, HashMap<String, Double>> dataBase;

    public Exchanges(Scanner input) {
        this.dataBase = new HashMap<>();
//        first line
        String line = input.nextLine();
        String[] firstLine = line.split(",");
//        ignore "Base"
        for (int i = 1; i < firstLine.length; i ++) {
            this.dataBase.put(firstLine[i], new HashMap<>());
        }
        while (input.hasNextLine()) {
            line = input.nextLine();
            String[] values = line.split(",");
            for (int i = 1; i < values.length; i ++) {
                this.dataBase.get(values[0]).put(firstLine[i], Double.parseDouble(values[i]));
            }
        }
    }
    public void exchange(User user, String sourceCurrency, String destCurrency, double amount) {
        Account sourceAccount = null;
        Account destAccount = null;
        for (Account i: user.getAccounts()) {
            if (i.getCurrency().equals(sourceCurrency)) {
                sourceAccount = i;
            }
            if (i.getCurrency().equals(destCurrency)) {
                destAccount = i;
            }
        }
        double sourceAmount = amount * this.dataBase.get(destCurrency).get(sourceCurrency);
        if (sourceAmount > sourceAccount.getAmount()) {
            System.out.println("Insufficient amount in account " + sourceCurrency + " for exchange");
            return;
        }
        if (sourceAmount >= sourceAccount.getAmount() / 2 && user.getPremium() == false) {
//          add commission
            sourceAmount += 0.01 * sourceAmount;
        }
        destAccount.addMoney(amount);
        sourceAccount.setAmount(sourceAccount.getAmount() - sourceAmount);
    }

}