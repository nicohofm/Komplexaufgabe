package Classes;

public class PictureData {
    private LicensePlate licensePlate;
    private String ownerFace;
    private char[][] picture;

    public PictureData(LicensePlate licensePlate, String ownerFace, char[][] picture)
    {
        this.licensePlate = licensePlate;
        this.ownerFace = ownerFace;
        this.picture = picture;
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

    public char[][] getPicture() {
        return picture;
    }
}
