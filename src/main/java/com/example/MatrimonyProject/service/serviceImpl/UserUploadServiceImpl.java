package com.example.MatrimonyProject.service.serviceImpl;

import com.example.MatrimonyProject.dto.UserUploadDTO;
import com.example.MatrimonyProject.exception.ResourceNotFoundException;
import com.example.MatrimonyProject.mapper.UserUploadMapper;
import com.example.MatrimonyProject.model.UserProfile;
import com.example.MatrimonyProject.model.UserUpload;
import com.example.MatrimonyProject.repo.UserProfileRepo;
import com.example.MatrimonyProject.repo.UserUploadRepo;
import com.example.MatrimonyProject.service.UserUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserUploadServiceImpl implements UserUploadService {

    private final UserUploadRepo userUploadRepo;
    private final UserUploadMapper  userUploadMapper;
    private final UserProfileRepo   userProfileRepo;


    @Override
    public UserUploadDTO create(Long userId, UserUploadDTO dto) {
        UserProfile user = userProfileRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id " + userId));

        UserUpload entity = userUploadMapper.toEntity(dto);
        entity.setUser(user);

        UserUpload saved = userUploadRepo.save(entity);
        user.getUploads().add(saved);
        userProfileRepo.save(user);

        return userUploadMapper.toDto(saved);
    }

    @Override
    public UserUploadDTO update(Long id, UserUploadDTO dto) {
        userUploadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserUpload not found with id " + id));

        dto.setId(id);
        UserUpload updated = userUploadMapper.toEntity(dto);
        return userUploadMapper.toDto(userUploadRepo.save(updated));
    }

    @Override
    public UserUploadDTO getById(Long id) {
        return userUploadRepo.findById(id)
                .map(userUploadMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("UserUpload not found with id " + id));
    }

    @Override
    public List<UserUploadDTO> getAll() {
        return userUploadRepo.findAll()
                .stream()
                .map(userUploadMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!userUploadRepo.existsById(id)) {
            throw new ResourceNotFoundException("UserUpload not found with id " + id);
        }
        userUploadRepo.deleteById(id);
    }

}
