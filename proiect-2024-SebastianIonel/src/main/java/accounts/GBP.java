package accounts;

public class GBP implements Account {
    private double amount;

    public GBP() {
        this.amount = 0;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void addMoney(double amount) {
        this.amount += amount;
    }

    @Override
    public void dropMoney(double amount) {
        this.amount -= amount;
    }
}