package com.example.MatrimonyProject.mapper;

import com.example.MatrimonyProject.dto.UserUploadDTO;
import com.example.MatrimonyProject.model.UserUpload;

public interface UserUploadMapper {

    UserUploadDTO toDto(UserUpload entity);
    UserUpload toEntity(UserUploadDTO dto);
}
