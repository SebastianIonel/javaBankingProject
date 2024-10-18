package printer;

import java.util.ArrayList;

public class RecommendedFormat {
    private ArrayList<String> stocksToBuy;

    public RecommendedFormat(ArrayList<String> stringArrayList) {
        this.stocksToBuy = stringArrayList;
    }

    public ArrayList<String> getStocksToBuy() {
        return stocksToBuy;
    }
}