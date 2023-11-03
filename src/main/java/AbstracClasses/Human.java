package AbstracClasses;

import java.util.Date;

public abstract class Human {
    protected String name;
    protected Date birthdate;
    protected String face;

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getFace() {
        return face;
    }
}
