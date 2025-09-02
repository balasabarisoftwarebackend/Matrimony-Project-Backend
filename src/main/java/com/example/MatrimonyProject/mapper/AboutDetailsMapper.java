package com.example.MatrimonyProject.mapper;

import com.example.MatrimonyProject.dto.AboutDetailsDTO;
import com.example.MatrimonyProject.model.AboutDetails;


public interface AboutDetailsMapper {

    AboutDetailsDTO toDto(AboutDetails entity);
    AboutDetails toEntity(AboutDetailsDTO dto);
}
