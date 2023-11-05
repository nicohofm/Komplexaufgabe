package Classes;

import cryptography.aes.AESEncryption;
import json.ReadWriteJson;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import Enums.MobileCentralUnit;

public class FineEngine {
    private final HashMap<int[], Double> fines;
    private int sequenceID = 1;
    private final MobileNetworkModule mobileNetworkModule;
    private final ReadWriteJson jsonConverter;
    private final AIEngine aiEngine;
    private final List<Record> recordList;

   public FineEngine(MobileNetworkModule mobileNetworkModule, CentralUnit centralUnit){
        aiEngine = new AIEngine();
        this.mobileNetworkModule = mobileNetworkModule;
        jsonConverter = new ReadWriteJson();
        fines = new HashMap<>();
        recordList = new ArrayList<>();
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
        return speed > 53;
    }
   public boolean checkFace(String face)
   {
        return mobileNetworkModule.isOwnerWanted(face);
   }

   public JSONObject getCarOwner(String licensePlate)
   {
       return jsonConverter.parseJSONString(mobileNetworkModule.checkLicensePlate(licensePlate));
   }

   public void createRecord(PictureData pictureData, String name, String birthDay, String phoneNumber, int allowedSpeed, int measuredSpeed, String manufacturer)
   {
        Record record = new Record();
        record.setPicture(pictureData.getPicture());
        record.setLicencePlate(pictureData.getLicensePlate().getId());
        record.setName(name);
        try{
           DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
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
        record.setPhoneNumber(phoneNumber);
        record.setAllowedSpeed(allowedSpeed);
        record.setMeasuredSpeed(measuredSpeed);
        record.setMeasuredSpeedAfterDeductionTolerance((int) (((double)measuredSpeed/100)*97));
        record.setPenalty(getPenalty(((double)measuredSpeed/100)*97));
        record.setManufacturer(manufacturer);
        recordList.add(record);
   }

    private double getPenalty(double measuredSpeedAfterDeductingTolerance){
        for (int[] key: fines.keySet()) {
            double speedDifference = measuredSpeedAfterDeductingTolerance - 53;
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
       return 1;
   }

    public void getMoney(String phoneNumber, double measuredSpeed){
       mobileNetworkModule.getSmartphone(phoneNumber).getMoney(getPenalty(measuredSpeed));
    }

    public AIEngine getAiEngine() {
        return aiEngine;
    }
    public List<Record> getRecordList()
    {
        return recordList;
    }
}
