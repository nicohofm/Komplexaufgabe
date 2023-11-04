package Classes;

import Interfaces.IEncryption;
import cryptography.sha.SHAEncryption;
import java.security.*;
import java.util.Base64;

public class IDCard{
    private final String magneticStrip;
    private SHAEncryption shaEncryption;
    //byte[] fingerprint;

    public IDCard(int magneticStrip/*, String fingerprint*/){
        shaEncryption = new SHAEncryption();
        this.magneticStrip = shaEncryption.encrypt(String.valueOf(magneticStrip));
        /*
        try
        {
            byte[] bytesOfFingerprint = fingerprint.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.fingerprint = md.digest(bytesOfFingerprint);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        */
    }

    /*
    public String getFingerprint()
    {
        return Base64.getEncoder().encodeToString(fingerprint);
    }
    */

    public String getMagneticStrip() {
        return magneticStrip;
    }
}
