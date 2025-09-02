package com.example.MatrimonyProject.service;

import com.example.MatrimonyProject.dto.ReligionDetailsDTO;

import java.util.List;

public interface ReligionDetailsService {

    ReligionDetailsDTO create(Long userId, ReligionDetailsDTO dto);
    ReligionDetailsDTO update(Long id, ReligionDetailsDTO dto);
    ReligionDetailsDTO getById(Long id);
    List<ReligionDetailsDTO> getAll();
    void delete(Long id);
}
