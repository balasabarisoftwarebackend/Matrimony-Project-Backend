package com.example.MatrimonyProject.service;

import com.example.MatrimonyProject.dto.UserUploadDTO;

import java.util.List;

public interface UserUploadService {

    UserUploadDTO create(Long userId, UserUploadDTO dto);
    UserUploadDTO update(Long id, UserUploadDTO dto);
    UserUploadDTO getById(Long id);
    List<UserUploadDTO> getAll();
    void delete(Long id);
}
