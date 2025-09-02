package com.example.MatrimonyProject.mapper.mapperImpl;

import com.example.MatrimonyProject.dto.ReligionDetailsDTO;
import com.example.MatrimonyProject.mapper.ReligionDetailsMapper;
import com.example.MatrimonyProject.model.ReligionDetails;
import org.springframework.stereotype.Component;

@Component
public class ReligionDetailsMapperImpl implements ReligionDetailsMapper {


    @Override
    public ReligionDetailsDTO toDto(ReligionDetails entity) {
        if (entity == null) return null;
        return ReligionDetailsDTO.builder()
                .id(entity.getId())
                .religion(entity.getReligion())
                .caste(entity.getCaste())
                .subCaste(entity.getSubCaste())
                .isInterCaste(entity.getIsInterCaste())
                .dosham(entity.getDosham())
                .willingToMarryOtherCaste(entity.getWillingToMarryOtherCaste())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public ReligionDetails toEntity(ReligionDetailsDTO dto) {
        if (dto == null) return null;
        ReligionDetails entity = ReligionDetails.builder()
                .id(dto.getId())
                .religion(dto.getReligion())
                .caste(dto.getCaste())
                .subCaste(dto.getSubCaste())
                .isInterCaste(dto.getIsInterCaste())
                .dosham(dto.getDosham())
                .willingToMarryOtherCaste(dto.getWillingToMarryOtherCaste())
                .build();
        return entity;
    }


}
