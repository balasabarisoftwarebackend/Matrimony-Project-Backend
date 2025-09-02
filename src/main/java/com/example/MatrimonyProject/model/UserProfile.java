package com.example.MatrimonyProject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Registration Info ---
    @Column(nullable = false, length = 32)
    private String createdFor;   // Myself / Son / Daughter / Relative

    @Column(nullable = false, length = 255)
    private String fullName;

    @Column(nullable = false, unique = true, length = 20)
    private String mobile;

    @Column(nullable = false, length = 255)
    private String passwordHash;   // hashed password

    // --- Basic Details ---
    private String email;
    private String gender;

    private LocalDate dob;
    private Integer age;

    private Integer preferredMinAge;
    private Integer preferredMaxAge;

    @Column(nullable = false, length = 20)
    private String profileStatus = "DRAFT";   // DRAFT / COMPLETE

    // --- Relations ---
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ReligionDetails religionDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PersonalDetails personalDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ProfessionalDetails professionalDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private AboutDetails aboutDetails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserUpload> uploads = new ArrayList<>();

    // --- Auditing ---
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // --- Helper methods for bidirectional sync ---
    public void setReligionDetails(ReligionDetails religionDetails) {
        this.religionDetails = religionDetails;
        if (religionDetails != null) {
            religionDetails.setUser(this);
        }
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
        if (personalDetails != null) {
            personalDetails.setUser(this);
        }
    }

    public void setProfessionalDetails(ProfessionalDetails professionalDetails) {
        this.professionalDetails = professionalDetails;
        if (professionalDetails != null) {
            professionalDetails.setUser(this);
        }
    }

    public void setAboutDetails(AboutDetails aboutDetails) {
        this.aboutDetails = aboutDetails;
        if (aboutDetails != null) {
            aboutDetails.setUser(this);
        }
    }

    public void addUpload(UserUpload upload) {
        uploads.add(upload);
        upload.setUser(this);
    }

    public void removeUpload(UserUpload upload) {
        uploads.remove(upload);
        upload.setUser(null);
    }

}
