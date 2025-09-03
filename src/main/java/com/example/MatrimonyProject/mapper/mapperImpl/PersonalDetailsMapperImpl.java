package com.example.MatrimonyProject.mapper.mapperImpl;

import com.example.MatrimonyProject.dto.PersonalDetailsDTO;
import com.example.MatrimonyProject.mapper.PersonalDetailsMapper;
import com.example.MatrimonyProject.model.PersonalDetails;
import com.example.MatrimonyProject.model.secondary.Language;
import com.example.MatrimonyProject.repo.secondaryRepo.LanguageRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component

public class PersonalDetailsMapperImpl implements PersonalDetailsMapper {

    private final LanguageRepo  languageRepo;

    public PersonalDetailsMapperImpl(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }


    @Override
    public PersonalDetailsDTO toDto(PersonalDetails entity) {
        if (entity == null) return null;
        return PersonalDetailsDTO.builder()
                .id(entity.getId())
                .maritalStatus(entity.getMaritalStatus())
                .numberOfChildren(entity.getNumberOfChildren())
                .nationality(entity.getNationality())
                .childrenLivingWith(entity.getChildrenLivingWith())
                .height(entity.getHeight())
                .familyStatus(entity.getFamilyStatus())
                .familyType(entity.getFamilyType())
                .motherTongue(entity.getMotherTongue() != null ? entity.getMotherTongue().getName() : null)
                .languagesKnown(
                        entity.getLanguagesKnown() != null
                                ? entity.getLanguagesKnown().stream()
                                .map(Language::getName)
                                .toList()
                                : List.of()
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public PersonalDetails toEntity(PersonalDetailsDTO dto) {
        if (dto == null) return null;

        return PersonalDetails.builder()
                .id(dto.getId())
                .maritalStatus(dto.getMaritalStatus())
                .nationality(dto.getNationality())
                .numberOfChildren(dto.getNumberOfChildren())
                .childrenLivingWith(dto.getChildrenLivingWith())
                .height(dto.getHeight())
                .familyStatus(dto.getFamilyStatus())
                .familyType(dto.getFamilyType())
                //.languagesKnown(Collections.emptyList()) // Initialize empty
                .languagesKnown(new ArrayList<>()) // ✅ never null
                .build();
    }


}
