package com.realestate.data.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Duration;
import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@Document
public class GatePass {

    @Id
    private String gatePassId;
    private String code;
    private LocalDateTime endTime;
    private LocalDateTime startTime = LocalDateTime.now();
    private Visitor visitor;
    private Type passType = Type.ENTRY;
    private boolean isValid = true;
    private String residentId;




    public String getDuration(LocalDateTime startTime, LocalDateTime endTime) {

        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return hours + ":" + minutes;
    }


}
