package com.realestate.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Visitor {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String purposeOfVisit;

    private String residentId;

}
