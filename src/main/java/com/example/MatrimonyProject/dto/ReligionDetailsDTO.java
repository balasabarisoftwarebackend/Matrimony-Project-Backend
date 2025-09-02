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
public class ReligionDetailsDTO {

    private Long id;
    private String religion;
    private String caste;
    private String subCaste;
    private Boolean isInterCaste;
    private String dosham;
    private Boolean willingToMarryOtherCaste;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
