package Classes;

import cryptography.aes.AESEncryption;
import cryptography.rsa.RSAEncryption;
import org.json.JSONString;
import Enums.MobileCentralUnit;

public class MobileNetworkModule {
    private final Police police;

    private final MobileCentralUnit mobileCentralUnit;
    private final VehicleRegistrationAuthority vehicleRegistrationAuthority;
    //private EncryptionAES encryptionAES;

    public MobileNetworkModule()
    {
        police = new Police();
        vehicleRegistrationAuthority = new VehicleRegistrationAuthority();
        mobileCentralUnit = MobileCentralUnit.INSTANCE;
    }

    public boolean isOwnerWanted(String face){
        /*//RSAEncryption
        RSAEncryption rsaEncryption = new RSAEncryption();
        String encryptedFace = rsaEncryption.encrypt(face);
        */
        AESEncryption aesEncryption = new AESEncryption();
        String encryptedFace = aesEncryption.encrypt(face);
        return police.isOwnerWanted(encryptedFace);
    }

    public String checkLicensePlate(String licensePlate){
        AESEncryption aesEncryption = new AESEncryption();
        String dataCarOwner = vehicleRegistrationAuthority.getOwnerData(aesEncryption.encrypt(licensePlate));
        return aesEncryption.decrypt(dataCarOwner);
    }

    public VehicleRegistrationAuthority getVehicleRegistrationAuthority()
    {
        return vehicleRegistrationAuthority;
    }

    public Police getPolice() {
        return police;
    }

    public SmartPhone getSmartphone(String phoneNumber)
    {
        return mobileCentralUnit.getSmartPhone(phoneNumber);
    }
}
