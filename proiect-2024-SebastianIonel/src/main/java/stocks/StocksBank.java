package stocks;

import java.util.*;

public class StocksBank {
    private HashMap<String, ArrayList<Double>> prices;
    private Set<String> recommended;
    public StocksBank(Scanner input) {
        this.prices = new HashMap<>();
//        ignore first line
        String line = input.nextLine();
        while (input.hasNextLine()) {
            line = input.nextLine();
            String[] values = line.split(",");
            ArrayList<Double> aux = new ArrayList<>();
//            add the prices for last 10 days
            for (int i = 1; i < values.length; i ++) {
                aux.add(Double.parseDouble(values[i]));
            }
//            values[0] == name
            this.prices.put(values[0], aux);
        }
    }
    public double buy(String name, int amount, boolean premium) {
//        9 == last day
        if (this.recommended == null) {
            this.recommend();
        }
        double price = this.prices.get(name).get(9);
        if (premium && this.recommended.contains(name)) {
            price = amount * price;
            return price - price / 20;
        }
        return amount * price;
    }

    public Set<String> recommend() {
        this.recommended = new HashSet<>();
        for (Map.Entry<String, ArrayList<Double>> entry: this.prices.entrySet()) {
            double lastTen = 0;
            double lastFive = 0;
            for (int i = 0; i < entry.getValue().size(); i ++) {
                lastTen += entry.getValue().get(i);
                if (i > 4) {
                    lastFive += entry.getValue().get(i);
                }
            }
            lastTen = lastTen / 10;
            lastFive = lastFive / 5;
            if (lastFive > lastTen) {
                this.recommended.add(entry.getKey());
            }
        }
        return this.recommended;
    }

    public ArrayList<String> recommendArray() {
        if (this.recommended == null) {
            this.recommend();
        }
        ArrayList<String> recommended = new ArrayList<>();
        for (String i: this.recommended) {
            recommended.add(i);
        }
        return recommended;
    }
}