package Classes;

public class SmartPhone {
    private String phoneNumber;
    private Wallet _wallet;

    public SmartPhone(String phoneNumber, Wallet wallet){
        this.phoneNumber = phoneNumber;
        this._wallet = wallet;
    }

    public void getMoney(double amount){
        _wallet.getMoney(amount);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Wallet getWallet() {
        return new Wallet(_wallet.getDeposit());
    }

    public void setWallet(Wallet wallet) {
        this._wallet = wallet;
    }
}
