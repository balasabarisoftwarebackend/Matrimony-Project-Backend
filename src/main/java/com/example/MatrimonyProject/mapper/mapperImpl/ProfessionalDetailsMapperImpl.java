package com.example.MatrimonyProject.mapper.mapperImpl;

import com.example.MatrimonyProject.dto.ProfessionalDetailsDTO;
import com.example.MatrimonyProject.mapper.ProfessionalDetailsMapper;
import com.example.MatrimonyProject.model.ProfessionalDetails;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalDetailsMapperImpl implements ProfessionalDetailsMapper {

    @Override
    public ProfessionalDetailsDTO toDto(ProfessionalDetails entity) {
        if (entity == null) return null;
        return ProfessionalDetailsDTO.builder()
                .id(entity.getId())
                .education(entity.getEducation())
                .specialization(entity.getSpecialization())
                .employedIn(entity.getEmployedIn())
                .occupation(entity.getOccupation())
                .annualIncome(entity.getAnnualIncome())
                .companyName(entity.getCompanyName())
                .jobTitle(entity.getJobTitle())
                .addressLine1(entity.getAddressLine1())
                .district(entity.getDistrict())
                .state(entity.getState())
                .city(entity.getCity())
                .country(entity.getCountry())
                .pincode(entity.getPincode())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public ProfessionalDetails toEntity(ProfessionalDetailsDTO dto) {
        if (dto == null) return null;
        return ProfessionalDetails.builder()
                .id(dto.getId())
                .education(dto.getEducation())
                .specialization(dto.getSpecialization())
                .employedIn(dto.getEmployedIn())
                .occupation(dto.getOccupation())
                .annualIncome(dto.getAnnualIncome())
                .companyName(dto.getCompanyName())
                .jobTitle(dto.getJobTitle())
                .addressLine1(dto.getAddressLine1())
                .district(dto.getDistrict())
                .state(dto.getState())
                .city(dto.getCity())
                .country(dto.getCountry())
                .pincode(dto.getPincode())
                .build();

    }

}
