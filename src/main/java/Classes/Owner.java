package Classes;

import AbstracClasses.Human;

import java.util.Date;

public class Owner extends Human {
    private SmartPhone smartPhone;
    private Car car;

    public Owner(String name, Date birthdate, String face)
    {
        smartPhone = new SmartPhone(new Wallet(5000));
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
}
