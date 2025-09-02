package com.example.MatrimonyProject.mapper;

import com.example.MatrimonyProject.dto.UserProfileDTO;
import com.example.MatrimonyProject.model.UserProfile;

public interface UserProfileMapper {

    UserProfileDTO toDto(UserProfile entity);
    UserProfile toEntity(UserProfileDTO dto);
}
