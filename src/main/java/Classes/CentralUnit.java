package Classes;

import Interfaces.IEncryption;
import com.sun.source.tree.Tree;

import java.awt.desktop.OpenFilesEvent;
import java.util.TreeMap;

public class CentralUnit {
    private TreeMap<Integer, Officer> registeredOfficers;
    private Record[] fineRecord;
    private LogEngine logEngine;
    private IEncryption encryptionSHA256;

    public void FillRegisteredOfficers(){

    }
    public boolean CheckOfficer(int id, int pin){
        return false;
    }
    public void AddRecord(Record record){

    }
    public void CreateReport(){

    }
    public void CreateExport(){

    }
}
