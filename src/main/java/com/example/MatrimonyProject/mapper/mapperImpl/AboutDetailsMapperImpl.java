package com.example.MatrimonyProject.mapper.mapperImpl;

import com.example.MatrimonyProject.dto.AboutDetailsDTO;
import com.example.MatrimonyProject.mapper.AboutDetailsMapper;
import com.example.MatrimonyProject.model.AboutDetails;
import org.springframework.stereotype.Component;

@Component
public class AboutDetailsMapperImpl implements AboutDetailsMapper {

    @Override
    public AboutDetailsDTO toDto(AboutDetails entity) {
        if (entity == null) return null;
        return AboutDetailsDTO.builder()
                .id(entity.getId())
                .aboutText(entity.getAboutText())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public AboutDetails toEntity(AboutDetailsDTO dto) {
        if (dto == null) return null;
        AboutDetails entity = AboutDetails.builder()
                .id(dto.getId())
                .aboutText(dto.getAboutText())
                .build();
        return entity;
    }

}
