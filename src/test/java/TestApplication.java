
import Classes.TraficSpikes;
import Classes.Wallet;
import cryptography.aes.AESEncryption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    public void testIsOwnerWanted() {
        AESEncryption aesEncryption = new AESEncryption();

        String face = "CAFFCFFAFCFEFAE";
        String expectedEncryptedFace = aesEncryption.encrypt(face);

        String encryptedFace = aesEncryption.encrypt(face);
        assertEquals(expectedEncryptedFace, encryptedFace);
    }

    @Test
    public void testCheckLicensePlate() {
        AESEncryption aesEncryption = new AESEncryption();

        String licensePlate = "HD5GHV";
        String dataCarOwner = aesEncryption.encrypt(licensePlate);
        String expectedDataCarOwner = aesEncryption.decrypt(dataCarOwner);

        assertEquals(expectedDataCarOwner, licensePlate);
    }

    @Test
    public void testLaunchMethodIsCalled() {
        TraficSpikes traficSpikes = mock(TraficSpikes.class);
        traficSpikes.launch();
        verify(traficSpikes, times(1)).launch();
    }

    @Test
    public void testGetMoney() {
        Wallet wallet = new Wallet(100); // Annahme: 100 Euro auf dem Konto

        double amountToWithdraw = 50.0; // Betrag zum Abheben
        wallet.getMoney(amountToWithdraw);

        double expectedDeposit = 100.0 - amountToWithdraw;
        assertEquals(expectedDeposit, wallet.getDeposit(), 0.01); // Toleranz von 0.01 für Gleitkommavergleiche
    }

    @Test
    public void testGetDeposit() {
        double money = 100.0;
        Wallet wallet = new Wallet(money);

        double deposit = wallet.getDeposit();
        assertEquals(money, deposit);
    }

    @Test
    public void getPanaltyTest() {
        HashMap<String, String> fineCatalog = new HashMap<>();
        ReadWriteJson readWriteJson = new ReadWriteJson();

        fineCatalog = readWriteJson.getFineCatalog("./data/bussgeld.json");
        FineEngine fineEngine = new FineEngine(new MobileNetworkModule(), new CentralUnit());
        fineEngine.loadFines(fineCatalog);

        LicensePlate licensePlate = new LicensePlate("XXXXXXX");
        String ownerFace = "ECEFEEAFEAAAEFC";

        PictureData pictureData = new PictureData(licensePlate, ownerFace, null);

        fineEngine.createRecord(pictureData, "Gustav Hardgas", "2000-01-03", "09344 929062", 50, 60, "Nissan");

        assertEquals(58.5, fineEngine.getRecordList().get(0).getPenalty());

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


        assertEquals(result.get("name"), fineEngine.getCarOwner("EEQ22M").get("name"));

        assertEquals(result.get("birthdate"), fineEngine.getCarOwner("EEQ22M").get("birthdate"));

        assertEquals(result.get("phoneNumber"), fineEngine.getCarOwner("EEQ22M").get("phoneNumber"));

    }

    @Test
    public void camMesureSpeed() {
        LicensePlate licensePlate = new LicensePlate("EEQ22M");
        Owner owner = new Owner("Leona Watts", LocalDate.parse("1956-12-20"), "ECCEACEAAEFAFEE", "0151 9610354287");


        Car car = new Car(licensePlate, "10w896vv39", "Tesla", owner);
        car.setSpeed(120);
        LaserScanner laserScanner = new LaserScanner();
        assertEquals(120, laserScanner.MeasureSpeed(car));

    }
}


