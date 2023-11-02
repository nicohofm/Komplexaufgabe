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
   public void loadFines(){}
    public void checkSpeed(){}
    public void processFelony(){}
    public void deductingSpeedTolerance(){}
    public void getPenalty(){}
    public void getMoney(){}
}
