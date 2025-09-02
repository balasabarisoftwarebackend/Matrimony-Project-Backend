package com.example.MatrimonyProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {

    private Long id;

    //Registration
    private String createdFor;
    private String fullName;
    private String mobile;

    // --- Basic Details ---
    private String email;
    private String gender;

    private LocalDate dob;
    private Integer age;

    private Integer preferredMinAge;
    private Integer preferredMaxAge;

    private String profileStatus = "DRAFT";   // DRAFT / COMPLETE

    private String passwordHash;  // plain password only for request

    // --- Relations ---
    private ReligionDetailsDTO religionDetails;

    private PersonalDetailsDTO personalDetails;

    private ProfessionalDetailsDTO professionalDetails;

    private AboutDetailsDTO aboutDetails;

    private List<UserUploadDTO> uploads = new ArrayList<>();

    // --- Auditing ---
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
