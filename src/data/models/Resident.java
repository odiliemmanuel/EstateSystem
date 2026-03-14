package data.models;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Resident {
    private String name;
    private String phoneNumber;
    private String id;
    private boolean isEnabled = true;
    private String email;
    private String houseAddress;
    private LocalDateTime dateRegistered = LocalDateTime.now();



}
