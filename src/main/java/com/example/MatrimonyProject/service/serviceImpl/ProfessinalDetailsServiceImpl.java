package com.example.MatrimonyProject.service.serviceImpl;

import com.example.MatrimonyProject.dto.ProfessionalDetailsDTO;
import com.example.MatrimonyProject.exception.ResourceNotFoundException;
import com.example.MatrimonyProject.mapper.ProfessionalDetailsMapper;
import com.example.MatrimonyProject.model.ProfessionalDetails;
import com.example.MatrimonyProject.model.UserProfile;
import com.example.MatrimonyProject.repo.ProfessionalDetailsRepo;
import com.example.MatrimonyProject.repo.UserProfileRepo;
import com.example.MatrimonyProject.service.ProfessinoalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfessinalDetailsServiceImpl implements ProfessinoalDetailsService {

    private final ProfessionalDetailsRepo professionalDetailsRepo;
    private final ProfessionalDetailsMapper professionalDetailsMapper;
    private final UserProfileRepo userProfileRepo;


    @Override
    public ProfessionalDetailsDTO create(Long userId, ProfessionalDetailsDTO dto) {
        UserProfile user = userProfileRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id " + userId));

        ProfessionalDetails entity = professionalDetailsMapper.toEntity(dto);
        entity.setUser(user);

        ProfessionalDetails saved = professionalDetailsRepo.save(entity);
        user.setProfessionalDetails(saved);
        userProfileRepo.save(user);

        return professionalDetailsMapper.toDto(saved);
    }

    @Override
    public ProfessionalDetailsDTO getById(Long id) {
        return professionalDetailsRepo.findById(id)
                .map(professionalDetailsMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("ProfessionalDetails not found with id " + id));
    }

    @Override
    public ProfessionalDetailsDTO update(Long id, ProfessionalDetailsDTO dto) {
        professionalDetailsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfessionalDetails not found with id " + id));

        dto.setId(id);
        ProfessionalDetails updated = professionalDetailsMapper.toEntity(dto);
        return professionalDetailsMapper.toDto(professionalDetailsRepo.save(updated));
    }

    @Override
    public List<ProfessionalDetailsDTO> getAll() {
        return professionalDetailsRepo.findAll()
                .stream()
                .map(professionalDetailsMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!professionalDetailsRepo.existsById(id)) {
            throw new ResourceNotFoundException("ProfessionalDetails not found with id " + id);
        }
        professionalDetailsRepo.deleteById(id);
    }
}
