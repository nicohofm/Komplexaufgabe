package Classes;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import cryptography.aes.*;

public class Police {
    private List<Owner> wantedOwner;
    private List<Owner> arrestedOwner;
    private List<Car> confiscatedCars;
    private AESEncryption aesEncryption;
    //private ReadWriteCSV csvReader;
    //private EncryptionAES encryptionAES;

    public void fillWantedOwner(List<String[]> userdata)
    {
        for (String[] data: userdata)
        {
            try{
                if(data[7].equals("yes"))
                {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = format.parse(data[4]);
                    wantedOwner.add(new Owner(data[3], date, data[4]));
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

        }
    }

    public boolean isOwnerWanted(String face){
        String faceIs = aesEncryption.decrypt(face);
        for (Owner owner : wantedOwner) {
            if (owner.getFace().equals(face)){
                return true;
            }
        }
        return false;
    }
}
