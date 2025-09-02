package com.example.MatrimonyProject.service.serviceImpl;

import com.example.MatrimonyProject.dto.AboutDetailsDTO;
import com.example.MatrimonyProject.exception.ResourceNotFoundException;
import com.example.MatrimonyProject.mapper.AboutDetailsMapper;
import com.example.MatrimonyProject.model.AboutDetails;
import com.example.MatrimonyProject.model.UserProfile;
import com.example.MatrimonyProject.repo.AboutDetailsRepo;
import com.example.MatrimonyProject.repo.UserProfileRepo;
import com.example.MatrimonyProject.service.AboutDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AboutDetailsServiceImpl implements AboutDetailsService {

    private final AboutDetailsRepo aboutDetailsRepo;
    private final AboutDetailsMapper  aboutDetailsMapper;
    private final UserProfileRepo  userProfileRepo;


    @Override
    public AboutDetailsDTO create(Long userId, AboutDetailsDTO dto) {
        UserProfile user = userProfileRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id " + userId));

        AboutDetails entity = aboutDetailsMapper.toEntity(dto);
        entity.setUser(user);

        AboutDetails saved = aboutDetailsRepo.save(entity);
        user.setAboutDetails(saved);
        userProfileRepo.save(user);

        return aboutDetailsMapper.toDto(saved);
    }

    @Override
    public AboutDetailsDTO update(Long id, AboutDetailsDTO dto) {
        aboutDetailsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AboutDetails not found with id " + id));

        dto.setId(id);
        AboutDetails updated = aboutDetailsMapper.toEntity(dto);
        return aboutDetailsMapper.toDto(aboutDetailsRepo.save(updated));
    }

    @Override
    public AboutDetailsDTO getById(Long id) {
        return aboutDetailsRepo.findById(id)
                .map(aboutDetailsMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("AboutDetails not found with id " + id));
    }

    @Override
    public List<AboutDetailsDTO> getAll() {
        return aboutDetailsRepo.findAll()
                .stream()
                .map(aboutDetailsMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!aboutDetailsRepo.existsById(id)) {
            throw new ResourceNotFoundException("AboutDetails not found with id " + id);
        }
        aboutDetailsRepo.deleteById(id);
    }
}
