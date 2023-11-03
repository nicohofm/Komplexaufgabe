package ExecuteProgram;

import CSV.ReadWriteCSV;
import Classes.*;
import json.ReadWriteJson;

import java.util.*;

public class Execute {
    private ParkingSpace parkingSpace;
    private SpeedCamera speedCamera;

    public void StartUp(){
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
