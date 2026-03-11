package data.models;

import java.time.LocalDateTime;

public class GatePass {

    private int visitorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String VisitorName;
    boolean isValid;
    private int id;

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getVisitorName() {
        return VisitorName;
    }

    public void setVisitorName(String visitorName) {
        VisitorName = visitorName;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    private String passCode;
}
