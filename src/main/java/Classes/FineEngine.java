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
    public void checkSpeed(int speed){}
    public void processFelony(Felony felony){}
    public void deductingSpeedTolerance(int allowedSpeed, int measuredSpeed){}
    public void getPenalty(int overSpeed){}
    public void getMoney(int phoneNumber, int amount){}
}
