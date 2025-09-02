package com.example.MatrimonyProject.controller.secondaryController;

import com.example.MatrimonyProject.dto.secondaryDto.LanguageDTO;
import com.example.MatrimonyProject.service.serviceImpl.secondaryService.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService service;

    @GetMapping
    public ResponseEntity<List<LanguageDTO>> getAllLanguages() {
        return ResponseEntity.ok(service.getAllLanguages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> getLanguage(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLanguageById(id));
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> createLanguage(@RequestBody LanguageDTO dto) {
        return ResponseEntity.ok(service.createLanguage(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<LanguageDTO>> bulkImport(@RequestBody List<LanguageDTO> dtos) {
        List<LanguageDTO> saved = dtos.stream()
                .map(service::createLanguage)
                .toList();
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<LanguageDTO> updateLanguage(@PathVariable Long id,
                                                      @RequestBody LanguageDTO dto) {
        return ResponseEntity.ok(service.updateLanguage(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        service.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }

}
