package com.example.MatrimonyProject.mapper;

import com.example.MatrimonyProject.dto.ReligionDetailsDTO;
import com.example.MatrimonyProject.model.ReligionDetails;

public interface ReligionDetailsMapper {

    ReligionDetailsDTO toDto(ReligionDetails entity);
    ReligionDetails toEntity(ReligionDetailsDTO dto);

}
