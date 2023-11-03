package Classes;

public class Camera {
    private Felony felony;

    public Camera()
    {
        felony = new Felony();
    }
    public Felony takePicture(Car car){
        felony.createPicture(car.getLicensePlate(), car.getOwner().getFace());
        return felony;
    }
}
