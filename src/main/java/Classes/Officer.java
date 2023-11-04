package Classes;

import AbstracClasses.Human;

import java.util.HashMap;

public class Officer extends Human {
    private int id;
    private IDCard idCard;

    public Officer(int id, int pin/*, String fingerprint*/)
    {
        this.id = id;
        idCard = new IDCard(pin/*, fingerprint*/);
    }
    public int getId() {
        return id;
    }

    public IDCard getIdCard() {
        return idCard;
    }
}
