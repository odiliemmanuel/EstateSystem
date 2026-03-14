package data.models;
import lombok.Data;


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

//    private String code;
//    private String residentName;
//    private String codeType;
//    private String validTill;
//    private String destination;
//}

}
