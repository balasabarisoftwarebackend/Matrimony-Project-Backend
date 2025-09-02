package com.example.MatrimonyProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDetailsDTO {

    private Long id;

    private String education;
    private String specialization;
    private String employedIn;  //Private /Govt / Business
    private String occupation;
    private Long annualIncome;

    private String companyName;
    private String jobTitle;

    //Address Info
    private String addressLine1;
    private String district;
    private String state;
    private String city;
    private String country;
    private String pincode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
