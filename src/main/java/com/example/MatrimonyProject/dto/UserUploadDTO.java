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
public class UserUploadDTO {

    private Long id;

    private String fileType;
    private String fileUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
