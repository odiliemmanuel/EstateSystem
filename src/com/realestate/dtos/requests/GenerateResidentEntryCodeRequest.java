package com.realestate.dtos.requests;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GenerateResidentEntryCodeRequest {

    String residentId;
    LocalDateTime validTill;
}
