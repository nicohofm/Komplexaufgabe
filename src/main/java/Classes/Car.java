package Classes;

public class Car {
    private final String manufacturer;
    private final String registrationID;
    private int speed;
    private final LicensePlate licensePlate;
    private final Owner owner;

    public Car(LicensePlate licensePlate, String registrationID, String manufacturer, Owner owner)
    {
        this.licensePlate = licensePlate;
        this.registrationID = registrationID;
        this.manufacturer = manufacturer;
        this.owner = owner;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public Owner getOwner() {
        return owner;
    }
}
