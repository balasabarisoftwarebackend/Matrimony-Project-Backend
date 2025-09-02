package com.example.MatrimonyProject.utilits;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileSearchRequest {

    // Core filters
    @Min(18) @Max(100)
    private Integer minAge;

    @Min(18) @Max(100)
    private Integer maxAge;

    private String gender;

    // Religion
    private String religion;
    private String caste;
    private String subCaste;

    // Personal
    private String maritalStatus;
    private String familyStatus;
    private String familyType;
    // ✅ Changed to IDs (foreign keys to Language table)
    private Long motherTongueId;         // instead of String
    private List<Long> languagesKnownIds; // instead of List<String>

    // Professional
    private String education;
    private String occupation;

    // Income (use Long for numeric compare)
    private Long annualIncomeMin;
    private Long annualIncomeMax;

    // Location
    private String city;
    private String state;
    private String country;

    // Text search on name (optional)
    private String q; // free text: matches fullName, occupation, companyName etc.



}
