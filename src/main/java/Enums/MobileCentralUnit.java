package Enums;

import java.util.HashMap;
import Classes.SmartPhone;

public enum MobileCentralUnit {
    INSTANCE;
    private final HashMap<String, SmartPhone> allSmartPhones = new HashMap<>();

     public void setSmartPhone(SmartPhone smartPhone){
        allSmartPhones.put(smartPhone.getPhoneNumber(), smartPhone);
    }

    public SmartPhone getSmartPhone(String phoneNumber){
        return allSmartPhones.get(phoneNumber);
    }
}
