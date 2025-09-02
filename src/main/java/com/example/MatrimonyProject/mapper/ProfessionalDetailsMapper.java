package com.example.MatrimonyProject.mapper;

import com.example.MatrimonyProject.dto.ProfessionalDetailsDTO;
import com.example.MatrimonyProject.model.ProfessionalDetails;

public interface ProfessionalDetailsMapper {

    ProfessionalDetailsDTO toDto(ProfessionalDetails entity);
    ProfessionalDetails toEntity(ProfessionalDetailsDTO dto);
}
