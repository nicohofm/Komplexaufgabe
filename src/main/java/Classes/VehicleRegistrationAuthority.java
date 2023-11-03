package Classes;

import cryptography.aes.AESEncryption;
import org.json.JSONObject;

import java.util.HashMap;

public class VehicleRegistrationAuthority {
    private HashMap<Car, Owner> carRegistrations;
    private AESEncryption aesEncryption;
    //private EncryptionAES encryptionAES;
    //private ReadWriteJson jsonConverter;
    //private ReadWriteCSV fileReader;

    public String getOwnerData(String licencePlate){
        String licencePlateIs = aesEncryption.decrypt(licencePlate);
        JSONObject jsonObject = new JSONObject();
        for (Car car : carRegistrations.keySet()) {

            if (car.getLicensePlate().getId().equals(licencePlateIs)){
                jsonObject.put("name", car.getOwner().getName());
                jsonObject.put("birthdate", car.getOwner().getBirthdate().toString());
                jsonObject.put("phoneNumber", car.getOwner().getSmartPhone().getPhoneNumber());
            }
        }

        return aesEncryption.encrypt(jsonObject.toString());
    }
}
