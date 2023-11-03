package ExecuteProgram;

import CSV.ReadWriteCSV;
import Classes.*;
import json.ReadWriteJson;

import java.util.*;

public class Execute {
    private ParkingSpace parkingSpace;
    private SpeedCamera speedCamera;
    private ReadWriteJson readWriteJson;
    private HashMap<String, String> fineCatolog;

    public Execute()
    {
        fineCatolog = new HashMap<>();
        parkingSpace = new ParkingSpace();
        speedCamera = new SpeedCamera();
    }

    public void StartUp(){
        ReadWriteCSV readWriteCSV = new ReadWriteCSV();
        speedCamera.setShutdown(false);
        List<String[]> data = readWriteCSV.getCsvData("data.csv");
        parkingSpace.fillParkingSpace(data);
        speedCamera.getMobileNetworkModule().getPolice().fillWantedOwner(data);
    }

    public void Import(){
        CentralUnit centralUnit = new CentralUnit();
        Scanner idInput = new Scanner(System.in);
        System.out.println("Give your id: ");
        int id = idInput.nextInt();

        Scanner pwInput = new Scanner(System.in);
        System.out.println("Give your password: ");
        int pw = pwInput.nextInt();

        boolean check = centralUnit.CheckOfficer(id, pw);
        if (!check){
            System.out.println("credentials incorrect");
            return;
        }
        else {
            Scanner answerInput = new Scanner(System.in);
            System.out.println("Start import y/n");
            String answer = answerInput.nextLine();
            if(Objects.equals(answer, "y")){
                fineCatolog = readWriteJson.getFineCatalog("./data/bussgeld.json");
            }
            if(Objects.equals(answer, "n")){
                return;
            }
        }
    }

    public void ExecuteSimulation(){
        FineEngine fineEngine = new FineEngine(speedCamera.getLed(), speedCamera.getCamera(), speedCamera.getMobileNetworkModule(), speedCamera.getCentralUnit());
        if(fineCatolog == null)
        {
            System.out.println("no data imported");
            return;
        }
        else {
            List<Car> cars = new LinkedList<>();
            Random rnd = new Random();
            for (int i = 0; i < 100; i++){
                for (int j = 0; j < 100; j++){
                    int column = rnd.nextInt(0, 50);
                    int row = rnd.nextInt(0, 20);
                    cars.add(parkingSpace.getCar(column, row));
                }
            }
            for (Car car: cars) {
                if(rnd.nextInt(0,10) < 1)
                {
                    if(rnd.nextInt(0, 100) < 15)
                    {
                        car.setSpeed(rnd.nextInt(74, 133));
                    }
                    else
                    {
                        car.setSpeed(rnd.nextInt(63, 73));
                    }
                }
                else {
                    car.setSpeed(rnd.nextInt(45, 54));
                }
                speedCamera.getLaserScanner().MeasureSpeed(car);
                if(fineEngine.checkSpeed(car.getSpeed(), car)){
                    speedCamera.getLed().Flash();
                    Felony felony = speedCamera.getCamera().takePicture(car);
                    speedCamera.getFineEngine().getAiEngine().extractPictureInformation(felony.getPicture());
                }
            }
        }
    }

    public void Report(){

    }

    public void Export(){

    }

    public void Shutdown(){
        speedCamera.setShutdown(true);
    }

    public void Exit(){
        System.exit(0);
    }
}
