package org.poo.cb;

import accounts.Account;
import accounts.Exchanges;
import printer.Printer;
import stocks.StocksBank;
import user.User;
import user.UserBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Structural == Facade
//Build == Singleton
public class FacadeBank {
    // links to objects
    private static FacadeBank instance;
    private Exchanges exchanges;
    private StocksBank stocksBank;
    private HashMap<String, User> users;

    //    private constructor
    private FacadeBank() {
        this.users = new HashMap<>();
    }

    public static FacadeBank getFacade() {
        if (instance == null) {
            instance = new FacadeBank();
        }
        return instance;
    }
    public static void reset() {
        instance = null;
    }
    private void makeCommands(Scanner input) {
        Printer printer = Printer.getInstance();
        while (input.hasNextLine()) {
            String command = input.nextLine();
//            System.out.println(command);
//            Format: command commandTarget ....
            String[] content = command.split(" ");
//            content[0] == command
            switch (content[0]) {
                case "CREATE": {
//                    only available command which start with create is create user
//                    format: command commandTarget email firstName lastName address
//                    verify if email already exist
                    if (this.users.containsKey(content[2])) {
                        System.out.println("User with " + content[2] + " already exists");
                    } else {
                        UserBuilder builder = new UserBuilder();
//                        director
                        Director.getInstance().makeUser(builder, content);
                        this.users.put(content[2], builder.getResult());
                    }
                    break;
                }
                case "ADD": {
//                    3 possible commands(ADD FRIEND, ADD MONEY, ADD ACCOUNT)
                    switch (content[1]) {
                        case "FRIEND": {
//                            content[2] == emailUser; content[3] == emailFriend
                            if (!this.users.containsKey(content[2])) {
                                System.out.println("User with " + content[2] + " doesn't exists");
                                break;
                            } else if (!this.users.containsKey(content[3])) {
                                System.out.println("User with " + content[3] + " doesn't exists");
                                break;
                            }
                            this.users.get(content[2]).addFriend(content[3]);
                            this.users.get(content[3]).addFriend(content[2]);
                            break;
                        }
                        case "MONEY": {
                            ArrayList<Account> currentAccounts = this.users.get(content[2]).getAccounts();
                            for (Account i: currentAccounts) {
                                if (i.getCurrency().equals(content[3])) {
                                    i.addMoney(Double.parseDouble(content[4]));
                                    break;
                                }
                            }
                            break;
                        }
                        case "ACCOUNT": {
                            this.users.get(content[2]).addAccount(content[3]);
                            break;
                        }
                    }
                    break;
                }
                case "TRANSFER": {
//                    format: command(0) command(1) email(2) friendEmail(3)
//                    currency(4) amount(5)
                    this.users.get(content[2]).transferMoney(this.users.get(content[3]),
                            content[4], Double.parseDouble(content[5]));
                    break;
                }
                case "BUY": {
//                    2 possible options: Stocks/Premium
                    if (content[1].equals("PREMIUM")) {
//                    format: command(0) command(1) email(2)
                    this.users.get(content[2]).buyPremium();
                    } else {
//                    format: command(0) command(1) email(2) company(3) noOfStocks(4)
                        double price = this.stocksBank.buy(content[3], Integer.parseInt(content[4]),
                                this.users.get(content[2]).getPremium());
                        this.users.get(content[2]).buyStocks(content[3], price, Integer.parseInt(content[4]));
                    }
                    break;
                }
                case "EXCHANGE": {
//                    format: command(0) command(1) email(2) sourceCurrency(3)
//                    destCurrency(4) amount(5)
                    this.exchanges.exchange(this.users.get(content[2]),
                            content[3], content[4], Double.parseDouble(content[5]));
                    break;
                }
                case "LIST": {
//                    format: LIST targetType email
//                    check if exist
                    if (!this.users.containsKey(content[2])) {
                        System.out.println("User with " + content[2] + " doesn't exist");
                    } else {
                        User currentUser = this.users.get(content[2]);
//                    send user to printer and the targetType
                        printer.getTask(currentUser, content[1]);
                    }
                    break;
                }
                case "RECOMMEND": {
                    ArrayList<String> recommended = this.stocksBank.recommendArray();
                    Printer.getInstance().simplePrintArray(recommended);
                    break;
                }
            }
        }
    }

    private void initExchange(Scanner input) {
        this.exchanges = new Exchanges(input);
    }

    private void initStocksBank(Scanner input) {
        this.stocksBank = new StocksBank(input);
    }
    public void sendFiles(String exchangeRates, String stockValues, String commands) {
        try {
//            exchanges
            File exchangesFile = new File("src/main/resources/" + exchangeRates);
            Scanner exchangesReader = new Scanner(exchangesFile);
            this.initExchange(exchangesReader);
//            stocks
            File stocksFile = new File("src/main/resources/" + stockValues);
            Scanner stocksReader = new Scanner(stocksFile);
            this.initStocksBank(stocksReader);
//            commands
            File file = new File("src/main/resources/" + commands);
            Scanner input = new Scanner(file);
            this.makeCommands(input);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}