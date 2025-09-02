package com.example.MatrimonyProject.service.serviceImpl;

import com.example.MatrimonyProject.dto.UserProfileDTO;
import com.example.MatrimonyProject.utilits.UserProfileSearchRequest;
import com.example.MatrimonyProject.exception.ResourceNotFoundException;
import com.example.MatrimonyProject.mapper.UserProfileMapper;
import com.example.MatrimonyProject.model.UserProfile;
import com.example.MatrimonyProject.repo.UserProfileRepo;
import com.example.MatrimonyProject.service.UserProfileService;
import com.example.MatrimonyProject.utilits.PageResponse;
import com.example.MatrimonyProject.utilits.UserProfileSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepo userProfileRepository;
    private final UserProfileMapper userProfileMapper;


    //To Create New User (POST)
    @Override
    public UserProfileDTO create(UserProfileDTO dto) {
        validateAgePreferences(dto);
        UserProfile entity = userProfileMapper.toEntity(dto);
        return userProfileMapper.toDto(userProfileRepository.save(entity));
    }


    //To Update User (PUT)
    @Override
    public UserProfileDTO update(Long id, UserProfileDTO dto) {
        UserProfile existing = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id " + id));

        dto.setId(id); // ensure correct id mapping
        validateAgePreferences(dto);
        UserProfile updated = userProfileMapper.toEntity(dto);
        updated.setId(existing.getId());

        return userProfileMapper.toDto(userProfileRepository.save(updated));
    }


    // To get User By Id (GET)
    @Override
    public UserProfileDTO getById(Long id) {
        return userProfileRepository.findById(id)
                .map(userProfileMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found with id " + id));
    }


    //To get all the User (GET)
    @Override
    public List<UserProfileDTO> getAll() {
        return userProfileRepository.findAll()
                .stream()
                .map(userProfileMapper::toDto)
                .toList();
    }


    //To delete the User By Id (DELETE)
    @Override
    public void delete(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserProfile not found with id " + id);
        }
        userProfileRepository.deleteById(id);
    }

    // Search with Filters
    @Override
    public PageResponse<UserProfileDTO> search(UserProfileSearchRequest request, Pageable pageable) {
        int size = Math.min(pageable.getPageSize(), 50); // defense-in-depth
        Pageable safe = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());

        var spec = UserProfileSpecification.withFilters(request);
        Page<UserProfile> page = userProfileRepository.findAll(spec, safe);

        List<UserProfileDTO> content = page.getContent()
                .stream()
                .map(userProfileMapper::toDto)
                .toList();

        return new PageResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

    // ✅ Validation method
    private void validateAgePreferences(UserProfileDTO dto) {
        Integer age = dto.getAge();
        Integer minAge = dto.getPreferredMinAge();
        Integer maxAge = dto.getPreferredMaxAge();
        String gender = dto.getGender();

        if (age == null || gender == null) {
            return; // skip validation if values are missing
        }

        // General rule: Min should not exceed Max
        if (minAge != null && maxAge != null && minAge > maxAge) {
            throw new IllegalArgumentException("Preferred Min Age cannot be greater than Preferred Max Age.");
        }

        // Male-specific rule
        if ("Male".equalsIgnoreCase(gender)) {
            if (maxAge != null && maxAge > age) {
                throw new IllegalArgumentException(
                        "For male profiles, Preferred Max Age cannot be greater than user's age."
                );
            }
        }

        // Female-specific rule
        if ("Female".equalsIgnoreCase(gender)) {
            if (minAge != null && minAge < age) {
                throw new IllegalArgumentException(
                        "For female profiles, Preferred Min Age must be greater than or equal to user's age."
                );
            }
        }
    }

}
