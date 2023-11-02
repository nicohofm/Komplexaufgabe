package Classes;

import Interfaces.IEncryption;

public class IDCard{
    private String magneticStrip;

    public IDCard(String magneticStrip){
        this.magneticStrip = magneticStrip;
    }

    public String getMagneticStrip() {
        return magneticStrip;
    }

    public void setMagneticStrip(String magneticStrip) {
        this.magneticStrip = magneticStrip;
    }
}
