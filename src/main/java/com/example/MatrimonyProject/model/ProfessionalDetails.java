package com.example.MatrimonyProject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "professional_details")
public class ProfessionalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserProfile user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
