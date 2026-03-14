package data.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Data
public class GatePass {

    private String code;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");
    private Visitor visitor;
    private int id;
    private Type passType;
    private boolean isValid = true;
    private int residentId;




    public String getDuration(LocalDateTime startTime, LocalDateTime endTime) {

        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return hours + ":" + minutes;
    }


}
