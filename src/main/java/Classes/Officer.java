package Classes;

import AbstracClasses.Human;

import java.util.HashMap;

public class Officer extends Human {
    private final int id;
    private final IDCard idCard;

    public Officer(int id, int pin/*, String fingerprint*/)
    {
        this.id = id;
        idCard = new IDCard(pin/*, fingerprint*/);
    }

    public IDCard getIdCard() {
        return idCard;
    }
}
