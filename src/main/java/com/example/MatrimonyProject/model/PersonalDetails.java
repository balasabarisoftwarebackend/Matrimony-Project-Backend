package com.example.MatrimonyProject.model;


import com.example.MatrimonyProject.model.secondary.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "personal_details")
public class PersonalDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maritalStatus;
    private String nationality;
    private Integer numberOfChildren;
    private String childrenLivingWith;

    private Integer height;
    private String familyStatus;
    private String familyType;




    // ✅ Known languages (many-to-many)
    @ManyToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST , CascadeType.MERGE})
    @JoinTable(
            name = "personal_languages",
            joinColumns = @JoinColumn(name = "personal_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languagesKnown = new ArrayList<>();

    // ✅ Mother tongue (one-to-one with Language)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST , CascadeType.MERGE})
    @JoinColumn(name = "mother_tongue_id")
    private Language motherTongue;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserProfile user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
