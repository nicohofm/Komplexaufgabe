package Classes;

public class SmartPhone {
    private int phoneNumber;
    private Wallet _wallet;

    public SmartPhone(Wallet wallet){
        this._wallet = wallet;
    }

    public void getMoney(int amount){

    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Wallet getWallet() {
        return new Wallet(_wallet.getDeposit());
    }

    public void setWallet(Wallet wallet) {
        this._wallet = wallet;
    }
}
