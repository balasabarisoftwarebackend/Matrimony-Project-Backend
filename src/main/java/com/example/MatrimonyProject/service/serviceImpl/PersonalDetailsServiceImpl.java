package com.example.MatrimonyProject.service.serviceImpl;

import com.example.MatrimonyProject.dto.PersonalDetailsDTO;
import com.example.MatrimonyProject.exception.ResourceNotFoundException;
import com.example.MatrimonyProject.mapper.PersonalDetailsMapper;
import com.example.MatrimonyProject.model.PersonalDetails;
import com.example.MatrimonyProject.model.UserProfile;
import com.example.MatrimonyProject.model.secondary.Language;
import com.example.MatrimonyProject.repo.PersonalDetailsRepo;
import com.example.MatrimonyProject.repo.UserProfileRepo;
import com.example.MatrimonyProject.repo.secondaryRepo.LanguageRepo;
import com.example.MatrimonyProject.service.PersonalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

   private final PersonalDetailsRepo personalDetailsRepo;
   private final PersonalDetailsMapper personalDetailsMapper;
   private final UserProfileRepo  userProfileRepo;
    private final LanguageRepo languageRepo;

    @Override
    public PersonalDetailsDTO create(Long userId, PersonalDetailsDTO dto) {
        UserProfile user = userProfileRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id " + userId));

        // Basic mapping
        PersonalDetails entity = personalDetailsMapper.toEntity(dto);
        entity.setUser(user);

        // Save first
        PersonalDetails saved = personalDetailsRepo.save(entity);

        // Mother tongue
        if (dto.getMotherTongue() != null) {
            Language motherTongue = languageRepo.findByNameIgnoreCase(dto.getMotherTongue())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid mother tongue: " + dto.getMotherTongue()));
            saved.setMotherTongue(motherTongue);
        }

        // Known languages
        if (dto.getLanguagesKnown() != null && !dto.getLanguagesKnown().isEmpty()) {
            List<Language> languages = dto.getLanguagesKnown().stream()
                    .map(name -> languageRepo.findByNameIgnoreCase(name)
                            .orElseThrow(() -> new ResourceNotFoundException("Invalid language: " + name)))
                    .toList();
            saved.setLanguagesKnown(languages);
        }

        // Save relationships
        saved = personalDetailsRepo.save(saved);

        // Update user
        user.setPersonalDetails(saved);
        userProfileRepo.save(user);

        return personalDetailsMapper.toDto(saved);
    }




    @Override
    public PersonalDetailsDTO update(Long id, PersonalDetailsDTO dto) {
        PersonalDetails existing = personalDetailsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonalDetails not found with id " + id));

        existing.setMaritalStatus(dto.getMaritalStatus());
        existing.setNationality(dto.getNationality());
        existing.setNumberOfChildren(dto.getNumberOfChildren());
        existing.setChildrenLivingWith(dto.getChildrenLivingWith());
        existing.setHeight(dto.getHeight());
        existing.setFamilyStatus(dto.getFamilyStatus());
        existing.setFamilyType(dto.getFamilyType());

        // Mother tongue
        if (dto.getMotherTongue() != null) {
            Language motherTongue = languageRepo.findByNameIgnoreCase(dto.getMotherTongue())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid mother tongue: " + dto.getMotherTongue()));
            existing.setMotherTongue(motherTongue);
        } else {
            existing.setMotherTongue(null);
        }

        // Languages
        if (dto.getLanguagesKnown() != null) {
            List<Language> languages = dto.getLanguagesKnown().stream()
                    .map(name -> languageRepo.findByNameIgnoreCase(name)
                            .orElseThrow(() -> new ResourceNotFoundException("Invalid language: " + name)))
                    .toList();
            existing.setLanguagesKnown(languages);
        } else {
            existing.setLanguagesKnown(new ArrayList<>()); // ✅ safe reset
        }

        return personalDetailsMapper.toDto(personalDetailsRepo.save(existing));
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
