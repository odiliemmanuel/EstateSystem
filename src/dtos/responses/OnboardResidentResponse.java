package dtos.responses;

import lombok.Data;

@Data
public class OnboardResidentResponse {

    private String dateRegistered;
    private String residentName;
    private String residentEmail;
    private boolean isEnabled;
}
