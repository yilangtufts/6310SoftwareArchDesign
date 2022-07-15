package edu.gatech.cs6310;

public class Pilot {

    // name:Finneas_Fig,phone:888-888-8888,taxID:890-12-3456,licenseID:panam_10,experience:33
    private String pilotAccount;
    private String name;
    private String phone;
    private String taxID;
    private String licenseID;
    private Integer experience;

    public Pilot(String pilotAccount, String lastName, String firstName, String phone, String taxID, String licenseID, Integer experience) {
        this.pilotAccount = pilotAccount;
        this.name = lastName+"_"+firstName;
        this.phone = phone;
        this.taxID = taxID;
        this.licenseID = licenseID;
        this.experience = experience;
    }

    public String getPilotAccount(){
        return this.pilotAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void undoExperience(){
        this.experience--;
    }
    public void gainExperience(){
        this.experience++;
    }
    @Override
    public String toString() {
        //display
        return "name:" + getName() + "," + "phone:" + getPhone() + "," + "taxID:" + getTaxID() + "," + "licenseID:" + getLicenseID() + "," + "experience:" + getExperience();
        //return "name:" + getStoreName() +"," + "revenue:" + getRevenue();
    }



}
