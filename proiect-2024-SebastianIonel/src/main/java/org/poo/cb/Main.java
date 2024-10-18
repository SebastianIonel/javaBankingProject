package org.poo.cb;


import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
//        numberOfArgs == 3
//      exchangesRates.csv stockValues.csv commands.txt
//      as path in this order
        if (args == null) {
            System.out.println("Running Main");
            return;
        } else if (args.length != 3) {
            System.out.println("Wrong number of arguments");
        }

        FacadeBank eBank = FacadeBank.getFacade();
        eBank.sendFiles(args[0], args[1], args[2]);
        eBank = null;
        FacadeBank.reset();
    }
}