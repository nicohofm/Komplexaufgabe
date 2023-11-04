package Classes;

public class PictureData {
    private final LicensePlate licensePlate;
    private final String ownerFace;
    private final char[][] picture;

    public PictureData(LicensePlate licensePlate, String ownerFace, char[][] picture)
    {
        this.licensePlate = licensePlate;
        this.ownerFace = ownerFace;
        this.picture = picture;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public String getOwnerFace() {
        return ownerFace;
    }

    public char[][] getPicture() {
        return picture;
    }
}
