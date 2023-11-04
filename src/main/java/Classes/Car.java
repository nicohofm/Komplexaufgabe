package Classes;

public class Car {
    private String manufacturer;
    private String registrationID;
    private int speed;
    private LicensePlate licensePlate;
    private Owner owner;
    private final char[][] frontSide = new char[16][8];

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
