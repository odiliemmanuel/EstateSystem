package data.models;

import java.time.LocalDateTime;

public class Resident {
    private String name;
    private String phoneNumber;
    private int id;
    private boolean isEnabled;
    private String email;
    private String houseAddress;
    private LocalDateTime dateRegistered;


    public Resident(String name, String phoneNumber,  String email, String houseAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.houseAddress = houseAddress;
        this.dateRegistered = LocalDateTime.now();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

}
