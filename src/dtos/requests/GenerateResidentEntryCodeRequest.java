package dtos.requests;
import lombok.Data;

import java.time.LocalTime;

@Data
public class GenerateResidentEntryCodeRequest {

    String residentId;
    LocalTime validTill;
}
