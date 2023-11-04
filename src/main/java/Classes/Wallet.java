package Classes;

public class Wallet {
    private double deposit;

    public Wallet(double deposit)
    {
        this.deposit = deposit;
    }

    public void getMoney(double amount){
        deposit = deposit - amount;
    }

    public double getDeposit() {
        return deposit;
    }
}
