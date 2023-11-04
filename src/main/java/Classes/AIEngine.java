package Classes;

public class AIEngine {

    public PictureData extractPictureInformation(char[][] picture){
        StringBuilder face = new StringBuilder();
        StringBuilder licensePlate = new StringBuilder();
        boolean isLicenseplate = false;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                char c = picture[i][j];
                if(isLicenseplate && Character.isLetter(c))
                {
                    licensePlate.append(c);
                }
                else {
                    if(c == '[')
                    {
                        isLicenseplate = true;
                    }
                    if (Character.isLetter(c)) {
                        face.append(c);
                    }
                }
            }
        }
        return new PictureData(new LicensePlate(licensePlate.toString()), face.toString(), picture);
    }
}
