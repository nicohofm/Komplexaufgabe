package Classes;

import AbstracClasses.Human;
import Enums.MobileCentralUnit;


import java.time.LocalDate;
import java.util.Date;



public class Owner extends Human {
    private final MobileCentralUnit mobileCentralUnit;
    private final SmartPhone smartPhone;
    private Car car;

    public Owner(String name, LocalDate birthdate, String face, String phoneNumber)
    {
        this.face = face;
        this.name = name;
        this.birthdate = birthdate;
        smartPhone = new SmartPhone(phoneNumber, new Wallet(5000));
        mobileCentralUnit = MobileCentralUnit.INSTANCE;
        mobileCentralUnit.setSmartPhone(this.smartPhone);
    }

    public SmartPhone getSmartPhone() {
        return smartPhone;
    }

    public String getFace()
    {
        return face;
    }
}
