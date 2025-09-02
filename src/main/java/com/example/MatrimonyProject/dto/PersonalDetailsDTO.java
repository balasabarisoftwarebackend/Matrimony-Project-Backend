package com.example.MatrimonyProject.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetailsDTO {


    private Long id;

    private String maritalStatus;
    private String nationality;
    private Integer numberOfChildren;
    private String childrenLivingWith;

    private Integer height;
    private String familyStatus;
    private String familyType;

    private String motherTongue;       // name, not ID
    private List<String> languagesKnown; // names, not IDs


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
