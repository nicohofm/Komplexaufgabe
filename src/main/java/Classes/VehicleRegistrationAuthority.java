package Classes;

import cryptography.aes.AESEncryption;
import cryptography.rsa.RSAEncryption;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class VehicleRegistrationAuthority {
    private HashMap<Car, Owner> carRegistrations;
    private AESEncryption aesEncryption;
    private RSAEncryption rsaEncryption;
    //private EncryptionAES encryptionAES;
    //private ReadWriteJson jsonConverter;
    //private ReadWriteCSV fileReader;

    public VehicleRegistrationAuthority()
    {
        aesEncryption = new AESEncryption();
        rsaEncryption = new RSAEncryption();
    }
    public void setCarRegistrations(List<Car> carList)
    {
        carRegistrations = new HashMap<>();
        for (Car car: carList)
        {
            carRegistrations.put(car, car.getOwner());
        }
    }
    public String getOwnerData(String licencePlate){
        //String licencePlateIS = rsaEncryption.decrypt(licencePlate);
        String licencePlateIs = aesEncryption.decrypt(licencePlate);
        JSONObject jsonObject = new JSONObject();
        for (Car car : carRegistrations.keySet()) {

            if (car.getLicensePlate().getId().equals(licencePlateIs)){
                jsonObject.put("name", car.getOwner().getName());
                jsonObject.put("birthdate", car.getOwner().getBirthdate().toString());
                jsonObject.put("phoneNumber", car.getOwner().getSmartPhone().getPhoneNumber());
            }
        }
        //String encryptedOwnerData = rsaEncryption.encrypt(jsonObject.toString());
        String encryptedOwnerData = aesEncryption.encrypt(jsonObject.toString());
        return encryptedOwnerData;
    }
}
