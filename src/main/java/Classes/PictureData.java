package Classes;

public class PictureData {
    private LicensePlate licensePlate;
    private String ownerFace;

    public PictureData(LicensePlate licensePlate, String ownerFace)
    {
        this.licensePlate = licensePlate;
        this.ownerFace = ownerFace;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getOwnerFace() {
        return ownerFace;
    }

    public void setOwnerFace(String ownerFace) {
        this.ownerFace = ownerFace;
    }
}
