public class SpeedCamera {
    private UUID serialNumber;
    private Date manufacturingDate;
    private boolean isShutdown;
    private MobileNetworkModule mobileNetworkModule;
    private LED led;
    private LaserScanner laserScanner;
    private CentralUnit centralUnit;
    private TraficSpikes traficSpikes;
    private Police police;
    private FineEngine fineEngine;
    private Camera camera;
    private ParkingSpace parkingSpace;

    public void setParkingSpace(ParkingSpace parkingSpace){}
    public void startup(){}
    public boolean checkOfficer(int id, int pin){return false;}
    public void incomingCar(Car car){}
    public void loadFines(String path){}
    public void arrestWantedOwnerAndCar(Car car){}
    public void stopCar(){}
    public void createReport(){}
    public void createExport(){}
    public void shutdown(){}
}
