package Classes;

import java.sql.Timestamp;

public class Felony {
    private char[][] picture;
    private Timestamp timestamp;

    public Felony(){
        picture = new char[16][8];
    }

    public void createPicture(LicensePlate licensePlate, String face)
    {
        picture = new char[][] {
            //0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15
            {'+','-','-','-','-','-','-','-','-','-','-','-','-','-','-','+'},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ','*','*','*','*','*',' ','|'},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ','*','*','*','*','*',' ','|'},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ','*','*','*','*','*',' ','|'},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'+','-','-','-','-','-','-','-','-','-','-','-','-','-','-','+'},
            {'+','-','-','-','[','*','*','*','*','*','*',']','-','-','-','+'},
            {'+','-','-','-','-','-','-','-','-','-','-','-','-','-','-','+'}
        };
        char[] splitFace = face.toCharArray();
        char[] splitLicensePlate = licensePlate.getId().toCharArray();
        int countFace = 0;
        for(int i = 1; i < 4; i++) {
            for (int j = 9; j < 14; j++) {
                picture[i][j] = splitFace[countFace];
                countFace++;
            }
        }

        int countLicense = 5;
        for (char letter: splitLicensePlate) {
            picture[6][countLicense] = letter;
            countLicense++;
        }

        long timeInNanos = System.nanoTime();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setNanos((int) (timeInNanos % 1000000000));
    }

    public char[][] getPicture() {
        return picture;
    }
}
