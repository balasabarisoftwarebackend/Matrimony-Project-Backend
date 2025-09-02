package com.example.MatrimonyProject.service.serviceImpl.secondaryService;

import com.example.MatrimonyProject.dto.secondaryDto.LanguageDTO;
import com.example.MatrimonyProject.model.secondary.Language;
import com.example.MatrimonyProject.repo.secondaryRepo.LanguageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepo repository;

    public List<LanguageDTO> getAllLanguages() {
        return repository.findAll().stream()
                .map(lang -> new LanguageDTO(lang.getId(), lang.getName()))
                .collect(Collectors.toList());
    }

    public LanguageDTO getLanguageById(Long id) {
        Language lang = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        return new LanguageDTO(lang.getId(), lang.getName());
    }

    public LanguageDTO createLanguage(LanguageDTO dto) {
        Language lang = Language.builder().name(dto.getName()).build();
        Language saved = repository.save(lang);
        return new LanguageDTO(saved.getId(), saved.getName());
    }

    public LanguageDTO updateLanguage(Long id, LanguageDTO dto) {
        Language lang = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        lang.setName(dto.getName());
        Language updated = repository.save(lang);
        return new LanguageDTO(updated.getId(), updated.getName());
    }

    public void deleteLanguage(Long id) {
        repository.deleteById(id);
    }

}
