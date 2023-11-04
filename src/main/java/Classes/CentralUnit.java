package Classes;

import Interfaces.IEncryption;
import cryptography.sha.SHAEncryption;
import com.sun.source.tree.Tree;

import java.awt.desktop.OpenFilesEvent;
import java.util.TreeMap;

public class CentralUnit {
    private TreeMap<Integer, Officer> registeredOfficers;
    private Record[] fineRecord;
    private LogEngine logEngine;
    private IEncryption encryptionSHA256;

    public CentralUnit()
    {
        registeredOfficers = new TreeMap<>();
    }

    public void FillRegisteredOfficers(int id, Officer officer){
        registeredOfficers.put(id, officer);
    }
    public boolean CheckOfficer(int id, int pin){
        SHAEncryption shaEncryption = new SHAEncryption();
        if (registeredOfficers.containsKey(id)){
            if(registeredOfficers.get(id).getIdCard().getMagneticStrip().equals(shaEncryption.encrypt(String.valueOf(pin))))
            {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /*//Check Fingerprint Officer
    public boolean CheckOfficer(int id, String fingerprint){
        if (registeredOfficers.containsKey(id)){
            if(registeredOfficers.get(id).getIdCard().getFingerprint().equals(fingerprint))
            {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }*/
    public void AddRecord(Record record){

    }
    public void CreateReport(){

    }
    public void CreateExport(){

    }
}
