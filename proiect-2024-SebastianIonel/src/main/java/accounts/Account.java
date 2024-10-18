package accounts;

public interface Account {
    public void setAmount(double amount);

    public double getAmount();

    public String getCurrency();

    public void addMoney(double amount);

    public void dropMoney(double amount);
}