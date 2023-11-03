package Classes;

import java.sql.Timestamp;
import java.util.Date;

public class Record {
    private char[][] picture = new char[16][8];
    private String licencePlate;
    private String name;
    private Date birthDate;
    private int phoneNumber;
    private int allowedSpeed;
    private int measuredSpeed;
    private int measuredSpeedAfterDeductionTolerance;
    private double penalty;
    private String manufacturer;
    private Timestamp nanoTimestamp;
    private String timestamp;

    private int sequenceID;

    public void setNanoTimestamp(Timestamp nanoTimestamp)
    {
        this.nanoTimestamp = nanoTimestamp;
    }
    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public void setSequenceID(int sequenceID) {
        this.sequenceID = sequenceID;
    }
    public Timestamp getNanoTimestamp()
    {
        return nanoTimestamp;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public int getSequenceID()
    {
        return sequenceID;
    }
    public String getManufacturer()
    {
        return manufacturer;
    }
    public String getTimestamp()
    {
        return timestamp;
    }
    public char[][] getPicture() {
        return picture;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAllowedSpeed() {
        return allowedSpeed;
    }

    public void setAllowedSpeed(int allowedSpeed) {
        this.allowedSpeed = allowedSpeed;
    }

    public int getMeasuredSpeed() {
        return measuredSpeed;
    }

    public void setMeasuredSpeed(int measuredSpeed) {
        this.measuredSpeed = measuredSpeed;
    }

    public int getMeasuredSpeedAfterDeductionTolerance() {
        return measuredSpeedAfterDeductionTolerance;
    }

    public void setMeasuredSpeedAfterDeductionTolerance(int measuredSpeedAfterDeductionTolerance) {
        this.measuredSpeedAfterDeductionTolerance = measuredSpeedAfterDeductionTolerance;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public void setPicture(char[][] picture)
    {
        this.picture = picture;
    }
}
