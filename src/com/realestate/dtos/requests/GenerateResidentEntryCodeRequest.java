package com.realestate.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class GenerateResidentEntryCodeRequest {

    @Id
    String residentId;
    LocalDateTime validTill;
}
