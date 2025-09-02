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
@Table(name = "religion_details")
public class ReligionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String religion;
    private String caste;
    private String subCaste;
    private Boolean isInterCaste;
    private String dosham;
    private Boolean willingToMarryOtherCaste;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserProfile user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
