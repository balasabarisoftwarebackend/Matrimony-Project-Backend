package com.example.MatrimonyProject.service;

import com.example.MatrimonyProject.dto.PersonalDetailsDTO;

import java.util.List;

public interface PersonalDetailsService {

    PersonalDetailsDTO create (Long userId, PersonalDetailsDTO dto);
    PersonalDetailsDTO update (Long id, PersonalDetailsDTO dto);
    PersonalDetailsDTO getById(Long id);
    List<PersonalDetailsDTO> getAll();
    void delete(Long id);

}
