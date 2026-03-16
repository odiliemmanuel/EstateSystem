package com.realEstate.data.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@Document
public class GatePass {

    private String code;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private Visitor visitor;
    private String gatePassId;
    private Type passType;
    private boolean isValid = true;
    private String residentId;



    public String getDuration(LocalDateTime startTime, LocalDateTime endTime) {

        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return hours + ":" + minutes;
    }


}
