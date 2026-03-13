package data.models;

public class Visitor {

    private String name;
    private String phoneNumber;
    private String purposeOfVisit;
    private int id;

    public Visitor(String name, String phoneNumber, String purposeOfVisit) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.purposeOfVisit = purposeOfVisit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
