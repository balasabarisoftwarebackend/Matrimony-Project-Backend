package com.example.MatrimonyProject.service;

import com.example.MatrimonyProject.dto.UserProfileDTO;
import com.example.MatrimonyProject.utilits.UserProfileSearchRequest;
import com.example.MatrimonyProject.utilits.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserProfileService {

    UserProfileDTO create(UserProfileDTO dto);
    UserProfileDTO update(Long id, UserProfileDTO dto);
    UserProfileDTO getById(Long id);
    List<UserProfileDTO> getAll();
    void delete(Long id);


    // Interface
    PageResponse<UserProfileDTO> search(UserProfileSearchRequest request, Pageable pageable);



//    // ✅ (Optional) Quick find by mobile/email - useful for login/validation
//    UserProfileDTO findByEmail(String email);
//    UserProfileDTO findByMobile(String mobile);
//
//    // ✅ (Optional) Lightweight list search (without pagination)
//    List<UserProfileDTO> searchList(UserProfileSearchRequest request);


}
