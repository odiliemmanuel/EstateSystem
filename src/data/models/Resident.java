package data.models;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Resident {
    @NonNull
    private String name;
    @NonNull
    private String phoneNumber;
    private String id;
    private boolean isEnabled;
    @NonNull
    private String email;
    @NonNull
    private String houseAddress;
    private LocalDateTime dateRegistered;




}
