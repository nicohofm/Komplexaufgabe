package Classes;

import java.util.HashMap;
import java.util.Map;

public class FineEngine {
    private HashMap<int[], Double> fines;
    private int sequenceID = 1;
    private LED led;
    private Camera camera;
    private MobileNetworkModule mobileNetworkModule;
    private CentralUnit centralUnit;
    //private ReadWriteJson jsonConverter;
    private AIEngine aiEngine;

   public FineEngine(LED led, Camera camera, MobileNetworkModule mobileNetworkModule, CentralUnit centralUnit){

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
    public boolean processFelony(Felony felony){return false;}
    public int deductingSpeedTolerance(int allowedSpeed, int measuredSpeed){return 1;}
    public int getPenalty(int overSpeed){return 1;}
    public void getMoney(int phoneNumber, int amount){}

    public AIEngine getAiEngine() {
        return aiEngine;
    }
}
