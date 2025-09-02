package com.example.MatrimonyProject.mapper;

import com.example.MatrimonyProject.dto.PersonalDetailsDTO;
import com.example.MatrimonyProject.model.PersonalDetails;

public interface PersonalDetailsMapper {
    
    PersonalDetailsDTO  toDto(PersonalDetails entity);
    PersonalDetails toEntity(PersonalDetailsDTO dto);
}
