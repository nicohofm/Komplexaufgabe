package Classes;

import java.util.HashMap;

public class FineEngine {
    private HashMap<Integer, Integer> fines;
    private int sequenceID = 1;
    private LED led;
    private Camera camera;
    private MobileNetworkModule mobileNetworkModule;
    private CentralUnit centralUnit;
    //private ReadWriteJson jsonConverter;
    private AIEngine aiEngine;

   public FineEngine(LED led, Camera camera, MobileNetworkModule mobileNetworkModule, CentralUnit centralUnit){

   }
   public void loadFines(String path){}
    public boolean checkSpeed(int speed){return false;}
    public boolean processFelony(Felony felony){return false;}
    public int deductingSpeedTolerance(int allowedSpeed, int measuredSpeed){return 1;}
    public int getPenalty(int overSpeed){return 1;}
    public void getMoney(int phoneNumber, int amount){}
}
