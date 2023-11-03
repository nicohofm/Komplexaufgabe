package Classes;

import cryptography.aes.AESEncryption;
import json.ReadWriteJson;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FineEngine {
    private HashMap<int[], Double> fines;
    private int sequenceID = 1;
    private MobileNetworkModule mobileNetworkModule;
    private CentralUnit centralUnit;
    private ReadWriteJson jsonConverter;
    private AIEngine aiEngine;
    private List<Record> recordList;

   public FineEngine(MobileNetworkModule mobileNetworkModule, CentralUnit centralUnit){
        aiEngine = new AIEngine();
        this.mobileNetworkModule = mobileNetworkModule;
        jsonConverter = new ReadWriteJson();
   }
    public void loadFines(HashMap<String, String> map){
        for (String key: map.keySet())
        {
            String[] tmpString = key.split("-");
            int[] tmpint = new int[tmpString.length];
            for (int i = 0; i < tmpString.length; i++)
            {
                tmpint[i] = Integer.parseInt(tmpString[i]);
            }
            fines.put(tmpint, Double.parseDouble(map.get(key)));
        }
    }
    public boolean checkSpeed(int speed, Car car){
        if (speed > 53){
            return true;
        }
        return false;
   }
   public boolean checkFace(String face)
   {
        AESEncryption aesEncryption = new AESEncryption();
        String encryptedFace = aesEncryption.encrypt(face);
        return mobileNetworkModule.isOwnerWanted(encryptedFace);
   }

   public JSONObject getCarOwner(String licensePlate)
   {
       AESEncryption aesEncryption = new AESEncryption();
       String dataCarOwner = mobileNetworkModule.checkLicensePlate(aesEncryption.encrypt(licensePlate));
       return jsonConverter.parseJSONString(aesEncryption.decrypt(dataCarOwner));
   }

   public void createRecord(PictureData pictureData, String name, String birthDay, String phoneNumber, int allowedSpeed, int measuredSpeed, String manufacturer)
   {
        Record record = new Record();
        record.setPicture(pictureData.getPicture());
        record.setName(name);
        try{
           DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           Date date = format.parse(birthDay);
           record.setBirthDate(date);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        record.setSequenceID(sequenceID);
        sequenceID = sequenceID + 1;
        long timeInNanos = System.nanoTime();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setNanos((int) (timeInNanos % 1000000000));
        record.setNanoTimestamp(timestamp);
        String s = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(timestamp);
        record.setTimestamp(s);
        record.setPhoneNumber(Integer.parseInt(phoneNumber));
        record.setAllowedSpeed(allowedSpeed);
        record.setMeasuredSpeed(measuredSpeed);
        record.setMeasuredSpeedAfterDeductionTolerance((measuredSpeed/100)*97);
        record.setPenalty(getPenalty((measuredSpeed/100)*97));
        record.setManufacturer(manufacturer);
        recordList.add(record);
   }

    public boolean processFelony(Felony felony){return false;}
    public int deductingSpeedTolerance(int allowedSpeed, int measuredSpeed){return 1;}
    private double getPenalty(int measuredSpeedAfterDeductingTolerance){
        for (int[] key: fines.keySet()) {
            int speedDifference = measuredSpeedAfterDeductingTolerance - 53;
            if(key.length == 1)
            {
                if(speedDifference > key[0])
                {
                    return fines.get(key);
                }
            }
            else
            {
                if(speedDifference >= key[0] && speedDifference <= key[1])
                {
                    return fines.get(key);
                }
            }
        }
       return 1;}
    public void getMoney(int phoneNumber, int amount){}

    public AIEngine getAiEngine() {
        return aiEngine;
    }
    public List<Record> getRecordList()
    {
        return recordList;
    }
}
