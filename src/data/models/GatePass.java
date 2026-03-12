package data.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GatePass {
    
    private String code;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private Visitor visitor;
    private String id;
    private Type passType;
    private boolean isValid;
    private String residentId;

}
