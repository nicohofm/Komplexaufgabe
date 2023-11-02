package Classes;

import AbstracClasses.Human;

public class Owner extends Human {
    private SmartPhone smartPhone;
    private Car car;

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
