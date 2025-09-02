package com.example.MatrimonyProject.service;

import com.example.MatrimonyProject.dto.AboutDetailsDTO;

import java.util.List;

public interface AboutDetailsService {

    AboutDetailsDTO create(Long userId, AboutDetailsDTO dto);
    AboutDetailsDTO update(Long id, AboutDetailsDTO dto);
    AboutDetailsDTO getById(Long id);
    List<AboutDetailsDTO> getAll();
    void delete(Long id);
}
