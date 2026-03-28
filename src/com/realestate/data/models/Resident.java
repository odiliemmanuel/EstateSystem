package com.realestate.data.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

@Data
@Document
public class Resident {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private boolean isEnabled = true;
    private String email;
    private String houseAddress;
    private LocalDateTime dateRegistered = LocalDateTime.now();


}
