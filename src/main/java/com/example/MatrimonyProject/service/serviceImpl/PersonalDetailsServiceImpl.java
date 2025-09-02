package com.example.MatrimonyProject.service.serviceImpl;

import com.example.MatrimonyProject.dto.PersonalDetailsDTO;
import com.example.MatrimonyProject.exception.ResourceNotFoundException;
import com.example.MatrimonyProject.mapper.PersonalDetailsMapper;
import com.example.MatrimonyProject.model.PersonalDetails;
import com.example.MatrimonyProject.model.UserProfile;
import com.example.MatrimonyProject.repo.PersonalDetailsRepo;
import com.example.MatrimonyProject.repo.UserProfileRepo;
import com.example.MatrimonyProject.service.PersonalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

   private final PersonalDetailsRepo personalDetailsRepo;
   private final PersonalDetailsMapper personalDetailsMapper;
   private final UserProfileRepo  userProfileRepo;


    @Override
    public PersonalDetailsDTO create(Long userId, PersonalDetailsDTO dto) {
        UserProfile user = userProfileRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id " + userId));

        PersonalDetails entity = personalDetailsMapper.toEntity(dto);
        entity.setUser(user);

        PersonalDetails saved = personalDetailsRepo.save(entity);
        user.setPersonalDetails(saved);
        userProfileRepo.save(user);

        return personalDetailsMapper.toDto(saved);
    }

    @Override
    public PersonalDetailsDTO update(Long id, PersonalDetailsDTO dto) {
        personalDetailsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonalDetails not found with id " + id));

        dto.setId(id);
        PersonalDetails updated = personalDetailsMapper.toEntity(dto);
        return personalDetailsMapper.toDto(personalDetailsRepo.save(updated));
    }

    @Override
    public PersonalDetailsDTO getById(Long id) {
        return personalDetailsRepo.findById(id)
                .map(personalDetailsMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("PersonalDetails not found with id " + id));
    }

    @Override
    public List<PersonalDetailsDTO> getAll() {
        return personalDetailsRepo.findAll()
                .stream()
                .map(personalDetailsMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!personalDetailsRepo.existsById(id)) {
            throw new ResourceNotFoundException("PersonalDetails not found with id " + id);
        }
        personalDetailsRepo.deleteById(id);
    }

}
