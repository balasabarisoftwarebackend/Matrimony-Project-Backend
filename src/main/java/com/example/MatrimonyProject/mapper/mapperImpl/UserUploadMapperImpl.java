package com.example.MatrimonyProject.mapper.mapperImpl;

import com.example.MatrimonyProject.dto.UserUploadDTO;
import com.example.MatrimonyProject.mapper.UserUploadMapper;
import com.example.MatrimonyProject.model.UserUpload;
import org.springframework.stereotype.Component;

@Component
public class UserUploadMapperImpl implements UserUploadMapper {

    @Override
    public UserUploadDTO toDto(UserUpload entity) {
        if (entity == null) return null;
        return UserUploadDTO.builder()
                .id(entity.getId())
                .fileType(entity.getFileType())
                .fileUrl(entity.getFileUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public UserUpload toEntity(UserUploadDTO dto) {
        if (dto == null) return null;
        return UserUpload.builder()
                .id(dto.getId())
                .fileType(dto.getFileType())
                .fileUrl(dto.getFileUrl())
                .build();
    }

}
