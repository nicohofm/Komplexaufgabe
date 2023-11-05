import CSV.ReadWriteCSV;
import Classes.*;

import static org.junit.jupiter.api.Assertions.*;


import json.ReadWriteJson;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;





public class TestApplication {

@Test
public void getPanaltyTest() {
     HashMap<String, String> fineCatalog = new HashMap<>();
    ReadWriteJson readWriteJson = new ReadWriteJson();

    fineCatalog = readWriteJson.getFineCatalog("./data/bussgeld.json");
    FineEngine fineEngine = new FineEngine(new MobileNetworkModule(),new CentralUnit());
    fineEngine.loadFines(fineCatalog);

    LicensePlate licensePlate = new LicensePlate("XXXXXXX");
    String ownerFace = "ECEFEEAFEAAAEFC";

    PictureData pictureData = new PictureData(licensePlate,ownerFace,null);

    fineEngine.createRecord(pictureData, "Gustav Hardgas","2000-01-03","09344 929062", 50, 60, "Nissan");

    assertEquals(58.5,fineEngine.getRecordList().get(0).getPenalty());

    }

    @Test
    public void getCarOwner() {

        ReadWriteCSV readWriteCSV = new ReadWriteCSV();
        ParkingSpace parkingSpace = new ParkingSpace();

        List<String[]> data = readWriteCSV.getCsvData("./data/data.csv");
        parkingSpace.fillParkingSpace(data);
        MobileNetworkModule mobileNetworkModule = new MobileNetworkModule();
        mobileNetworkModule.getVehicleRegistrationAuthority().setCarRegistrations(parkingSpace.getCarList());

        FineEngine fineEngine = new FineEngine(mobileNetworkModule, new CentralUnit());

        JSONObject temp = new JSONObject();
        JSONObject result = new JSONObject();
        result.put("name", "Leona Watts");
        result.put("birthdate", "1956-12-20");
        result.put("phoneNumber", "0151 9610354287");


        assertEquals(result.get("name"),fineEngine.getCarOwner("EEQ22M").get("name"));

        assertEquals(result.get("birthdate"),fineEngine.getCarOwner("EEQ22M").get("birthdate"));

        assertEquals(result.get("phoneNumber"),fineEngine.getCarOwner("EEQ22M").get("phoneNumber"));

    }

    @Test
    public void camMesureSpeed(){
    LicensePlate licensePlate = new LicensePlate("EEQ22M");
    Owner owner = new Owner("Leona Watts", LocalDate.parse("1956-12-20") ,"ECCEACEAAEFAFEE","0151 9610354287");


    Car car = new Car(licensePlate,"10w896vv39", "Tesla", owner);
    car.setSpeed(120);
    LaserScanner laserScanner = new LaserScanner();
    assertEquals(120,laserScanner.MeasureSpeed(car));

    }








}