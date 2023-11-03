package Classes;

import cryptography.aes.AESEncryption;
import java.util.HashMap;
import java.util.Map;

public class FineEngine {
    private HashMap<int[], Double> fines;
    private int sequenceID = 1;
    private MobileNetworkModule mobileNetworkModule;
    private CentralUnit centralUnit;
    //private ReadWriteJson jsonConverter;
    private AIEngine aiEngine;

   public FineEngine(MobileNetworkModule mobileNetworkModule, CentralUnit centralUnit){
        aiEngine = new AIEngine();
        this.mobileNetworkModule = mobileNetworkModule;
   }
    public void loadFines(HashMap<String, String> map){
        for (String key: map.keySet())
        {
            String[] tmpString = key.split("-");
            int[] tmpint = new int[tmpString.length];
            for (int i = 0; i < tmpString.length; i++)
            {
                tmpint[i] = Integer.parseInt(tmpString[i]);
            }
            fines.put(tmpint, Double.parseDouble(map.get(key)));
        }
    }
    public boolean checkSpeed(int speed, Car car){
        if (speed > 53){
            return true;
        }
        return false;
   }
   public void checkFace(String face)
   {
        AESEncryption aesEncryption = new AESEncryption();
        String encryptedFace = aesEncryption.encrypt(face);
        mobileNetworkModule.isOwnerWanted(encryptedFace);
   }

   public void getCarOwner(String licensePlate)
   {
       mobileNetworkModule.checkLicensePlate();
   }

    public boolean processFelony(Felony felony){return false;}
    public int deductingSpeedTolerance(int allowedSpeed, int measuredSpeed){return 1;}
    public int getPenalty(int overSpeed){
        /*for (int[] key: fines.keySet()) {
            int speedDifference = speed - 53;
            if(key.length == 1)
            {
                if(speedDifference > key[0])
                {
                }
            }
            else
            {
                if(speedDifference > key[0] && )
            }
        }*/
       return 1;}
    public void getMoney(int phoneNumber, int amount){}

    public AIEngine getAiEngine() {
        return aiEngine;
    }
}
