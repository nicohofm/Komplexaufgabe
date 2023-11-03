package Classes;

import java.util.Date;
import java.util.UUID;

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

    public SpeedCamera()
    {
        mobileNetworkModule = new MobileNetworkModule();
        this.isShutdown = true;
    }

    public void setShutdown(boolean shutdown) {
        isShutdown = shutdown;
    }


    //public void setParkingSpace(ParkingSpace parkingSpace){}
    //public void startup(){isShutdown = false;}
    public boolean checkOfficer(int id, int pin){return false;}
    public void incomingCar(Car car){}
    public void loadFines(String path){}
    public void arrestWantedOwnerAndCar(Car car){}
    public void stopCar(){}
    public void createReport(){}
    public void createExport(){}
    public MobileNetworkModule getMobileNetworkModule()
    {
        return mobileNetworkModule;
    }

    public LED getLed() {
        return led;
    }

    public Camera getCamera() {
        return camera;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }

    public FineEngine getFineEngine() {
        return fineEngine;
    }

    public LaserScanner getLaserScanner() {
        return laserScanner;
    }

    //public void shutdown(){isShutdown = true;}
}
