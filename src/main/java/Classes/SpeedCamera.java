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
    private EMP emp;

    public SpeedCamera()
    {
        mobileNetworkModule = new MobileNetworkModule();
        this.isShutdown = true;
        traficSpikes = new TraficSpikes();
        laserScanner = new LaserScanner();
        centralUnit = new CentralUnit();
        camera = new Camera();
        led = new LED();
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
    public void stopCar(){
        traficSpikes.launch();
        emp.activateEMP();
    }
    public void createReport(){}
    public void createExport(){}
    public MobileNetworkModule getMobileNetworkModule()
    {
        return mobileNetworkModule;
    }
    public void setFineEngine(FineEngine fineEngine)
    {
        this.fineEngine = fineEngine;
    }
    public TraficSpikes getTraficSpikes() {
        return traficSpikes;
    }

    public boolean getIsShutdown()
    {
        return isShutdown;
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
