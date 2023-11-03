package Classes;

import org.json.JSONString;
import Enums.MobileCentralUnit;

public class MobileNetworkModule {
    private Police police;

    private MobileCentralUnit mobileCentralUnit;
    private VehicleRegistrationAuthority vehicleRegistrationAuthority;
    //private EncryptionAES encryptionAES;

    public MobileNetworkModule()
    {
        police = new Police();
    }

    public boolean isOwnerWanted(String face){
        return police.isOwnerWanted(face);
    }

    public String checkLicensePlate(String licensePlate){return vehicleRegistrationAuthority.getOwnerData(licensePlate);}

    public Police getPolice() {
        return police;
    }
}
