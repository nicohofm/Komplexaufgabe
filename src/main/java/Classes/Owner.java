package Classes;

import AbstracClasses.Human;
import Enums.MobileCentralUnit;

import java.util.Date;

public class Owner extends Human {
    private SmartPhone smartPhone;
    private Car car;

    public Owner(String name, LocalDate birthdate, String face, String phoneNumber)
    {
        this.face = face;
        this.name = name;
        this.birthdate = birthdate;
        smartPhone = new SmartPhone(phoneNumber, new Wallet(5000));
    }

    public SmartPhone getSmartPhone() {
        return smartPhone;
    }

    public void setSmartPhone(SmartPhone smartPhone) {
        this.smartPhone = smartPhone;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getFace()
    {
        return face;
    }
}
