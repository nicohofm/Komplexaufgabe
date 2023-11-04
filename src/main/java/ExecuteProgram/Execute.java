package ExecuteProgram;

import CSV.ReadWriteCSV;
import Classes.*;
import Classes.Record;
import json.ReadWriteJson;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class Execute {
    private ParkingSpace parkingSpace;
    private SpeedCamera speedCamera;
    private FineEngine fineEngine;
    private ReadWriteJson readWriteJson;
    private HashMap<String, String> fineCatalog;

    public Execute()
    {
        fineCatalog = new HashMap<>();
        parkingSpace = new ParkingSpace();
        speedCamera = new SpeedCamera();
        readWriteJson = new ReadWriteJson();
    }

    public void StartUp(){
        ReadWriteCSV readWriteCSV = new ReadWriteCSV();
        speedCamera.setShutdown(false);
        List<String[]> data = readWriteCSV.getCsvData("./data/data.csv");
        parkingSpace.fillParkingSpace(data);
        speedCamera.getMobileNetworkModule().getPolice().fillWantedOwner(data);
        speedCamera.getMobileNetworkModule().getVehicleRegistrationAuthority().setCarRegistrations(parkingSpace.getCarList());
    }

    public void Import(){
        if(!speedCamera.getIsShutdown()) {
            CentralUnit centralUnit = new CentralUnit();
            centralUnit.FillRegisteredOfficers(1, new Officer(1, 1234));
            Scanner idInput = new Scanner(System.in);
            System.out.println("Give your id: ");
            int id = idInput.nextInt();

            Scanner pwInput = new Scanner(System.in);
            System.out.println("Give your pin or fingerprint: ");
            int pw = pwInput.nextInt();

            boolean check = centralUnit.CheckOfficer(id, pw);
            if (!check) {
                System.out.println("credentials incorrect");
                return;
            } else {
                Scanner answerInput = new Scanner(System.in);
                System.out.println("Start import y/n");
                String answer = answerInput.nextLine();
                if (Objects.equals(answer, "y")) {
                    fineCatalog = readWriteJson.getFineCatalog("./data/bussgeld.json");
                    fineEngine = new FineEngine(speedCamera.getMobileNetworkModule(), speedCamera.getCentralUnit());
                    speedCamera.setFineEngine(fineEngine);
                    speedCamera.getFineEngine().loadFines(fineCatalog);
                }
                if (Objects.equals(answer, "n")) {
                    return;
                }
            }
        }
    }

    public void ExecuteSimulation(){
        if(fineCatalog == null)
        {
            System.out.println("no data imported");
            return;
        }
        else {
            List<Car> cars = new LinkedList<>();
            Random rnd = new Random();
            for (int i = 0; i < 100; i++){
                for (int j = 0; j < 100; j++){
                    boolean gotACar = false;
                    while(!gotACar)
                    {
                        int column = rnd.nextInt(0, 49);
                        int row = rnd.nextInt(0, 19);
                        if(parkingSpace.getCar(column, row) != null)
                        {
                            cars.add(parkingSpace.getCar(column, row));
                            gotACar = true;
                        }
                    }
                }
            }
            for (int j = 0; j < cars.size(); j++) {
                if(rnd.nextInt(0,100) < 10)
                {
                    if(rnd.nextInt(0, 100) < 15)
                    {
                        cars.get(j).setSpeed(rnd.nextInt(74, 133));
                    }
                    else
                    {
                        cars.get(j).setSpeed(rnd.nextInt(63, 73));
                    }
                }
                else {
                    cars.get(j).setSpeed(rnd.nextInt(45, 54));
                }
                speedCamera.getLaserScanner().MeasureSpeed(cars.get(j));
                if(fineEngine.checkSpeed(cars.get(j).getSpeed(), cars.get(j))){
                    boolean ownerwanted = false;
                    speedCamera.getLed().Flash();
                    Felony felony = speedCamera.getCamera().takePicture(cars.get(j));
                    PictureData pictureData = speedCamera.getFineEngine().getAiEngine().extractPictureInformation(felony.getPicture());
                    ownerwanted = speedCamera.getFineEngine().checkFace(pictureData.getOwnerFace());
                    JSONObject jsonObject = speedCamera.getFineEngine().getCarOwner(pictureData.getLicensePlate().getId());
                    speedCamera.getFineEngine().createRecord(pictureData, jsonObject.get("name").toString(),
                            jsonObject.get("birthdate").toString(), jsonObject.get("phoneNumber").toString(),
                        50, cars.get(j).getSpeed(), cars.get(j).getManufacturer());
                    if(ownerwanted)
                    {
                        speedCamera.getTraficSpikes().launch();
                        speedCamera.getMobileNetworkModule().getPolice().arrestOwner(cars.get(j).getOwner());
                        speedCamera.getMobileNetworkModule().getPolice().confiscateCar(cars.get(j));
                        parkingSpace.deleteCar(cars.get(j));
                        for (int i = 0; i<cars.size(); i++) {
                            if(cars.get(i).getLicensePlate().getId().equals(cars.get(j).getLicensePlate().getId()))
                            {
                                cars.remove(car);
                            }
                        }
                    }
                }
            }
        }
    }

    public void Report(){
        List<Record> recordList = speedCamera.getFineEngine().getRecordList();
        Map<String, Long> manufacturerAndCountingMap = recordList.stream().collect(Collectors.groupingBy(Record::getManufacturer, Collectors.counting()));
        Comparator<Record> timestampAsc = Comparator.comparing(Record::getNanoTimestamp);
        List<Record> recordOverviewAscTimestamp = recordList.stream().sorted(timestampAsc).toList();
        Comparator<Record> measuredSpeedDesc = (Record record1, Record record2) -> record2.getMeasuredSpeed() - record1.getMeasuredSpeed();
        List<Record> recordOverviewDescSpeed = recordList.stream().sorted(measuredSpeedDesc).toList();
        int minSpeed = recordList.stream().mapToInt(Record::getMeasuredSpeed).min().orElseThrow(NoSuchElementException::new);
        double averageSpeed = recordList.stream().collect(Collectors.averagingInt(Record::getMeasuredSpeed));
        int maxSpeed = recordList.stream().mapToInt(Record::getMeasuredSpeed).max().orElseThrow(NoSuchElementException::new);
        Logger logger = Logger.getLogger("report");
        FileHandler fh;
        try {
            fh = new FileHandler("./data/report.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        logger.info("Anzahl der records, gruppiert nach manufacturer");
        for (String key: manufacturerAndCountingMap.keySet()) {
            logger.info(key + " " + String.valueOf(manufacturerAndCountingMap.get(key)));
        }
        logger.info("-----------------------------------------");
        logger.info("Übersicht der records, aufsteigendsortiert nach timestamp");
        recordOverviewAscTimestamp.forEach(record -> logger.info(record.getNanoTimestamp()+" "+record.getName()+" "+ record.getBirthDate() +" "+record.getPhoneNumber()+" "+record.getLicencePlate()+" "+record.getMeasuredSpeed()));
        logger.info("-----------------------------------------");
        logger.info("Übersicht der records, absteigend sortiert nach measuredSpeed");
        recordOverviewDescSpeed.forEach(record -> logger.info(String.valueOf(record.getMeasuredSpeed())+" "+record.getNanoTimestamp()+" "+record.getName()+" "+ record.getBirthDate() +" "+record.getPhoneNumber()+" "+record.getLicencePlate()));
        logger.info("-----------------------------------------");
        logger.info("Min Speed: " + String.valueOf(minSpeed));
        logger.info("-----------------------------------------");
        logger.info("Average Speed: " + String.valueOf(averageSpeed));
        logger.info("-----------------------------------------");
        logger.info("Max Speed: " + String.valueOf(maxSpeed));

    }

    public void Export(){
        ReadWriteCSV readWriteCSV = new ReadWriteCSV();
        List<List<String>> exportList = new ArrayList<>();
        List<Record> recordList = speedCamera.getFineEngine().getRecordList();
        Comparator<Record> timestampAsc = Comparator.comparing(Record::getNanoTimestamp);
        List<Record> recordOverviewAscTimestamp = recordList.stream().sorted(timestampAsc).toList();
        for (Record record: recordOverviewAscTimestamp) {
            List<String> tmpList = Arrays.asList(String.valueOf(record.getSequenceID()), record.getNanoTimestamp().toString(), record.getTimestamp(),
                    /*record.getPicture(), */record.getLicencePlate(), record.getName(), record.getBirthDate().toString(), String.valueOf(record.getPhoneNumber()), String.valueOf(record.getAllowedSpeed()),
                    String.valueOf(record.getMeasuredSpeed()), String.valueOf(record.getMeasuredSpeedAfterDeductionTolerance()), String.valueOf(record.getPenalty()));
            exportList.add(tmpList);
        }
        readWriteCSV.writeFile(exportList,"./data/export.csv");
        /*
        AESEncryption aesEncryption = new AESEncryption();
        aesEncryption.encryptFile("./data/export.csv", "./data/export.enc");
        */
    }

    public void Shutdown(){
        speedCamera.setShutdown(true);
    }

    public void Exit(){
        System.exit(0);
    }
}
