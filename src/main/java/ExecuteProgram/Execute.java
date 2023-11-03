package ExecuteProgram;

import Classes.*;

public class Execute {
    private ParkingSpace parkingSpace;
    private SpeedCamera speedCamera;

    public Execute()
    {
        parkingSpace = new ParkingSpace();
        speedCamera = new SpeedCamera();
    }

    public void StartUp(){
        speedCamera.setShutdown(false);
        parkingSpace.fillParkingSpace();

    }

    public void Import(){

    }

    public void ExecuteSimulation(){

    }

    public void Report(){

    }

    public void Export(){

    }

    public void Shutdown(){

    }

    public void Exit(){

    }
}
