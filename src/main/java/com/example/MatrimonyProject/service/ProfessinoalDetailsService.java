package com.example.MatrimonyProject.service;

import com.example.MatrimonyProject.dto.ProfessionalDetailsDTO;

import java.util.List;

public interface ProfessinoalDetailsService {

    ProfessionalDetailsDTO create(Long userId, ProfessionalDetailsDTO dto);
    ProfessionalDetailsDTO getById(Long id);
    ProfessionalDetailsDTO update (Long id, ProfessionalDetailsDTO dto);
    List<ProfessionalDetailsDTO> getAll();
    void delete(Long id);
}
