package data.models;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
public class Resident {
    @NonNull
    private String name;
    @NonNull
    private String phoneNumber;
    private int id;
    private boolean isEnabled;
    @NonNull
    private String email;
    @NonNull
    private String houseAddress;
    private LocalDateTime dateRegistered;


}
