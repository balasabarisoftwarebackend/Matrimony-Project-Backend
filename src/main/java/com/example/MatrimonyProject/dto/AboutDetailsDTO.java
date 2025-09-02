package com.example.MatrimonyProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AboutDetailsDTO {

    private Long id;
    private String aboutText;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
