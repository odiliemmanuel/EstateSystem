package com.realestate.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Visitor {

    private String name;
    private String phoneNumber;
    private String purposeOfVisit;
    private int id;
    private String residentId;

}
