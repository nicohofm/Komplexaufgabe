package Classes;

import org.json.JSONString;

public class MobileNetworkModule {
    private Police police;
    private VehicleRegistrationAuthority vehicleRegistrationAuthority;
    //private EncryptionAES encryptionAES;

    public MobileNetworkModule()
    {
        police = new Police();
    }

    public boolean isOwnerWanted(String face){
        return police.isOwnerWanted(face);
    }

    public JSONString checkLicensePlate(String licensePlate){return null;}

    public Police getPolice() {
        return police;
    }
}
