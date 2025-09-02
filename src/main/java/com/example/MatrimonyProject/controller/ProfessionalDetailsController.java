package com.example.MatrimonyProject.controller;

import com.example.MatrimonyProject.dto.ProfessionalDetailsDTO;
import com.example.MatrimonyProject.service.ProfessinoalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professional-details")
@RequiredArgsConstructor
public class ProfessionalDetailsController {

    private final ProfessinoalDetailsService professinoalDetailsService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ProfessionalDetailsDTO> create(@PathVariable Long userId, @RequestBody ProfessionalDetailsDTO dto) {
        return ResponseEntity.ok(professinoalDetailsService.create(userId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalDetailsDTO> update(@PathVariable Long id, @RequestBody ProfessionalDetailsDTO dto) {
        return ResponseEntity.ok(professinoalDetailsService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDetailsDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(professinoalDetailsService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalDetailsDTO>> getAll() {
        return ResponseEntity.ok(professinoalDetailsService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professinoalDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
