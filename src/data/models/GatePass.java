package data.models;
import lombok.Data;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class GatePass {

    private String code;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a");
    private Visitor visitor;
    private int id;
    private Type passType;
    private boolean isValid;
    private int residentId;


    public GatePass(String code, String startTime, String endTime, int residentId, Visitor visitor) {
        this.code = code;
        this.startTime = LocalDateTime.parse(startTime, formatter);
        this.endTime = LocalDateTime.parse(endTime, formatter);
        this.visitor = visitor;
    }

    public String getDuration() {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return hours + ":" + minutes;
    }


}
