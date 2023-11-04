package Classes;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cryptography.aes.*;

public class Police {
    private final List<Owner> wantedOwner;
    private final List<Owner> arrestedOwner;
    private final List<Car> confiscatedCars;
    private final AESEncryption aesEncryption;
    //private ReadWriteCSV csvReader;
    //private EncryptionAES encryptionAES;

    public Police()
    {
        confiscatedCars = new ArrayList<>();
        arrestedOwner = new ArrayList<>();
        wantedOwner = new ArrayList<>();
        aesEncryption = new AESEncryption();
    }

    public void fillWantedOwner(List<String[]> userdata)
    {
        for (String[] data: userdata)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try{
                if(data[7].equals("yes"))
                {
                    Date convertedCurrentDate = format.parse(data[4]);
                    String date=format.format(convertedCurrentDate);
                    LocalDate localDate = LocalDate.parse(date);
                    wantedOwner.add(new Owner(data[3], localDate, data[4], data[6]));
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

        }
    }
    public void arrestOwner(Owner owner)
    {
        arrestedOwner.add(owner);
    }
    public void confiscateCar(Car car)
    {
        confiscatedCars.add(car);
    }

    public boolean isOwnerWanted(String face){
        String faceIs = aesEncryption.decrypt(face);
        for (Owner owner : wantedOwner) {
            if (owner.getFace().equals(faceIs)){
                return true;
            }
        }
        return false;
    }
}
