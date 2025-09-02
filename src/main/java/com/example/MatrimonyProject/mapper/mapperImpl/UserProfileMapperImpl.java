package com.example.MatrimonyProject.mapper.mapperImpl;

import com.example.MatrimonyProject.dto.UserProfileDTO;
import com.example.MatrimonyProject.dto.UserUploadDTO;
import com.example.MatrimonyProject.mapper.*;
import com.example.MatrimonyProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserProfileMapperImpl implements UserProfileMapper {

    private final ReligionDetailsMapper religionDetailsMapper;
    private final PersonalDetailsMapper personalDetailsMapper;
    private final ProfessionalDetailsMapper professionalDetailsMapper;
    private final AboutDetailsMapper aboutDetailsMapper;
    private final UserUploadMapper userUploadMapper;

    @Autowired
    public UserProfileMapperImpl(ReligionDetailsMapper religionDetailsMapper,
                                 PersonalDetailsMapper personalDetailsMapper,
                                 ProfessionalDetailsMapper professionalDetailsMapper,
                                 AboutDetailsMapper aboutDetailsMapper,
                                 UserUploadMapper userUploadMapper) {
        this.religionDetailsMapper = religionDetailsMapper;
        this.personalDetailsMapper = personalDetailsMapper;
        this.professionalDetailsMapper = professionalDetailsMapper;
        this.aboutDetailsMapper = aboutDetailsMapper;
        this.userUploadMapper = userUploadMapper;
    }


    @Override
    public UserProfileDTO toDto(UserProfile entity) {
        if (entity == null) return null;

        List<UserUploadDTO> uploadDTOs = null;
        if (entity.getUploads() != null) {
            uploadDTOs = new ArrayList<>();
            for (UserUpload u : entity.getUploads()) {
                UserUploadDTO dto = userUploadMapper.toDto(u);
                uploadDTOs.add(dto);
            }
        }

        return UserProfileDTO.builder()
                .id(entity.getId())
                .createdFor(entity.getCreatedFor())
                .fullName(entity.getFullName())
                .mobile(entity.getMobile())
                .email(entity.getEmail())
                .gender(entity.getGender())
                .dob(entity.getDob())
                .age(entity.getAge())
                .preferredMinAge(entity.getPreferredMinAge())
                .preferredMaxAge(entity.getPreferredMaxAge())
                .profileStatus(entity.getProfileStatus())
                .religionDetails(religionDetailsMapper.toDto(entity.getReligionDetails()))
                .personalDetails(personalDetailsMapper.toDto(entity.getPersonalDetails()))
                .professionalDetails(professionalDetailsMapper.toDto(entity.getProfessionalDetails()))
                .aboutDetails(aboutDetailsMapper.toDto(entity.getAboutDetails()))
                .uploads(uploadDTOs)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }


    @Override
    public UserProfile toEntity(UserProfileDTO dto) {
        if (dto == null) return null;

        UserProfile entity = UserProfile.builder()
                .id(dto.getId())
                .createdFor(dto.getCreatedFor())
                .fullName(dto.getFullName())
                .mobile(dto.getMobile())
                .email(dto.getEmail())
                .gender(dto.getGender())
                .dob(dto.getDob())
                .age(dto.getAge())
                .preferredMinAge(dto.getPreferredMinAge())
                .preferredMaxAge(dto.getPreferredMaxAge())
                .profileStatus(dto.getProfileStatus() == null ? "DRAFT" : dto.getProfileStatus())
                .build();


        // 🔑 Handle password (plain → hashed)
        if (dto.getPasswordHash() != null) {
            // Better to hash in Service using PasswordEncoder
            entity.setPasswordHash(dto.getPasswordHash());
        }


        // Map and attach child entities — also set child.user = entity for bidirectional relationship
        if (dto.getReligionDetails() != null) {
            ReligionDetails r = religionDetailsMapper.toEntity(dto.getReligionDetails());
            r.setUser(entity);
            entity.setReligionDetails(r);
        }

        if (dto.getPersonalDetails() != null) {
            PersonalDetails p = personalDetailsMapper.toEntity(dto.getPersonalDetails());
            p.setUser(entity);
            entity.setPersonalDetails(p);
        }

        if (dto.getProfessionalDetails() != null) {
            ProfessionalDetails prof = professionalDetailsMapper.toEntity(dto.getProfessionalDetails());
            prof.setUser(entity);
            entity.setProfessionalDetails(prof);
        }

        if (dto.getAboutDetails() != null) {
            AboutDetails about = aboutDetailsMapper.toEntity(dto.getAboutDetails());
            about.setUser(entity);
            entity.setAboutDetails(about);
        }

        if (dto.getUploads() != null) {
            List<UserUpload> uploads = new ArrayList<>();
            for (UserUploadDTO uDto : dto.getUploads()) {
                UserUpload u = userUploadMapper.toEntity(uDto);
                u.setUser(entity);
                uploads.add(u);
            }
            entity.setUploads(uploads);
        }

        return entity;
    }


}
