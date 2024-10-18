package stocks;

public class Stocks {
    private String stockName;
    private int amount;
    public Stocks(String stockName) {
        this.amount = 0;
        this.stockName = stockName;
    }

    public String getStockName() {
        return this.stockName;
    }
    public int getAmount() {
        return this.amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void addAmount(double amount) {
        this.amount += amount;
    }
}