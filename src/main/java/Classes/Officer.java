package Classes;

import AbstracClasses.Human;

import java.util.HashMap;

public class Officer extends Human {
    private int id;
    private IDCard idCard;

    public Officer(int id)
    {
        this.id = id;
        idCard = new IDCard("1234");
    }
    public int getId() {
        return id;
    }

    public IDCard getIdCard() {
        return idCard;
    }
}
