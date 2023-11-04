package Enums;

import java.util.HashMap;
import Classes.SmartPhone;

public enum MobileCentralUnit {
    ;
    private final HashMap<Integer, SmartPhone> allSmartPhones = new HashMap<>();

     public void setSmartPhone(SmartPhone smartPhone){
        allSmartPhones.put(smartPhone.getPhoneNumber(), smartPhone);
    }
    public SmartPhone getSmartPhone(int phoneNumber){
        return allSmartPhones.get(phoneNumber);
    }
}
