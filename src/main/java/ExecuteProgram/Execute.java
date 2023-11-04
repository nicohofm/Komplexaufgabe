package ExecuteProgram;

import CSV.ReadWriteCSV;
import Classes.*;
import Classes.Record;
import json.ReadWriteJson;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                fineCatalog = readWriteJson.getFineCatalog("./data/bussgeld.json");
                fineEngine = new FineEngine(speedCamera.getMobileNetworkModule(), speedCamera.getCentralUnit());
                speedCamera.setFineEngine(fineEngine);
                speedCamera.getFineEngine().loadFines(fineCatalog);
            }
            if(Objects.equals(answer, "n")){
                return;
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
                        int column = rnd.nextInt(0, 50);
                        int row = rnd.nextInt(0, 20);
                        if(parkingSpace.getCar(column, row) != null)
                        {
                            cars.add(parkingSpace.getCar(column, row));
                            gotACar = true;
                        }
                    }
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
                    boolean ownerwanted = false;
                    speedCamera.getLed().Flash();
                    Felony felony = speedCamera.getCamera().takePicture(car);
                    PictureData pictureData = speedCamera.getFineEngine().getAiEngine().extractPictureInformation(felony.getPicture());
                    ownerwanted = speedCamera.getFineEngine().checkFace(pictureData.getOwnerFace());
                    JSONObject jsonObject = speedCamera.getFineEngine().getCarOwner(pictureData.getLicensePlate().getId());
                    speedCamera.getFineEngine().createRecord(pictureData, jsonObject.get("name").toString(),
                            jsonObject.get("birthDay").toString(), jsonObject.get("phoneNumber").toString(),
                 50, car.getSpeed(), car.getManufacturer());
                    if(ownerwanted)
                    {
                        speedCamera.getTraficSpikes().launch();
                        speedCamera.getMobileNetworkModule().getPolice().arrestOwner(car.getOwner());
                        parkingSpace.deleteCar(car);
                        for (Car remainingCar: cars) {
                            if(remainingCar.getLicensePlate().getId().equals(car.getLicensePlate().getId()))
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
        recordOverviewDescSpeed.forEach(record -> logger.info(record.getMeasuredSpeed()+" "+record.getNanoTimestamp()+" "+record.getName()+" "+ record.getBirthDate() +" "+record.getPhoneNumber()+" "+record.getLicencePlate()));
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
    }

    public void Shutdown(){
        speedCamera.setShutdown(true);
    }

    public void Exit(){
        System.exit(0);
    }
}
