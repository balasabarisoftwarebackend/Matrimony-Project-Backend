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

    // 🔹 Helper to resolve language safely
    private Language resolveLanguage(String name) {
        return languageRepo.findByNameIgnoreCase(name.trim())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid language: " + name));
    }

    @Override
    @Transactional
    public PersonalDetailsDTO create(Long userId, PersonalDetailsDTO dto) {
        UserProfile user = userProfileRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id " + userId));

        // Map basic fields
        PersonalDetails entity = personalDetailsMapper.toEntity(dto);
        entity.setUser(user);

        // Save first
        PersonalDetails saved = personalDetailsRepo.save(entity);

        // Set mother tongue
        if (dto.getMotherTongue() != null && !dto.getMotherTongue().isBlank()) {
            Language motherTongue = resolveLanguage(dto.getMotherTongue());
            saved.setMotherTongue(motherTongue);
        }

        // Set languagesKnown
        if (dto.getLanguagesKnown() != null && !dto.getLanguagesKnown().isEmpty()) {
            List<Language> langs = dto.getLanguagesKnown()
                    .stream()
                    .map(this::resolveLanguage)
                    .collect(Collectors.toList());
            saved.setLanguagesKnown(langs);
        }

        // Save again
        saved = personalDetailsRepo.save(saved);

        // Link to user
        user.setPersonalDetails(saved);
        userProfileRepo.save(user);

        // Reload with relationships
        PersonalDetails reloaded = personalDetailsRepo.findById(saved.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Failed to reload personal details"));

        return personalDetailsMapper.toDto(reloaded);
    }


    @Override
    public PersonalDetailsDTO update(Long id, PersonalDetailsDTO dto) {
        PersonalDetails existing = personalDetailsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonalDetails not found with id " + id));

        // 🔹 Update basic fields
        existing.setMaritalStatus(dto.getMaritalStatus());
        existing.setNationality(dto.getNationality());
        existing.setNumberOfChildren(dto.getNumberOfChildren());
        existing.setChildrenLivingWith(dto.getChildrenLivingWith());
        existing.setHeight(dto.getHeight());
        existing.setFamilyStatus(dto.getFamilyStatus());
        existing.setFamilyType(dto.getFamilyType());

        // 🔹 Update mother tongue
        if (dto.getMotherTongue() != null && !dto.getMotherTongue().isBlank()) {
            existing.setMotherTongue(resolveLanguage(dto.getMotherTongue()));
        } else {
            existing.setMotherTongue(null);
        }

        // 🔹 Update languages
        if (dto.getLanguagesKnown() != null && !dto.getLanguagesKnown().isEmpty()) {
            List<Language> languages = dto.getLanguagesKnown()
                    .stream()
                    .map(this::resolveLanguage)
                    .collect(Collectors.toList());
            existing.setLanguagesKnown(languages);
        } else {
            existing.getLanguagesKnown().clear();
        }

        PersonalDetails updated = personalDetailsRepo.save(existing);
        return personalDetailsMapper.toDto(updated);
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
