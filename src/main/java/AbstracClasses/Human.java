package AbstracClasses;

import java.time.LocalDate;
import java.util.Date;

public abstract class Human {
    protected String name;
    protected LocalDate birthdate;
    protected String face;

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getFace() {
        return face;
    }
}
