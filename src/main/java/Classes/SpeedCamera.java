package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SpeedCamera {
    private UUID serialNumber;
    private String manufacturingDate;
    private boolean isShutdown;
    private final MobileNetworkModule mobileNetworkModule;
    private final LED led;
    private final LaserScanner laserScanner;
    private final CentralUnit centralUnit;
    private final TraficSpikes traficSpikes;
    private FineEngine fineEngine;
    private final Camera camera;
    //private ParkingSpace parkingSpace;
    // private EMP emp;

    public SpeedCamera()
    {
        serialNumber = UUID.randomUUID();
        manufacturingDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
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
    /*public void stopCar(){
        traficSpikes.launch();
        emp.activateEMP();
    }*/
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

    public CentralUnit getCentralUnit() { return centralUnit; }

    public FineEngine getFineEngine() {
        return fineEngine;
    }

    public LaserScanner getLaserScanner() {
        return laserScanner;
    }

    //public void shutdown(){isShutdown = true;}
}
