package com.example.MatrimonyProject.controller;

import com.example.MatrimonyProject.dto.PersonalDetailsDTO;
import com.example.MatrimonyProject.service.PersonalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal-details")
@RequiredArgsConstructor
public class PersonalDetailsController {

    private final PersonalDetailsService personalDetailsService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<PersonalDetailsDTO> create(@PathVariable Long userId, @RequestBody PersonalDetailsDTO dto) {
        return ResponseEntity.ok(personalDetailsService.create(userId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalDetailsDTO> update(@PathVariable Long id, @RequestBody PersonalDetailsDTO dto) {
        return ResponseEntity.ok(personalDetailsService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalDetailsDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personalDetailsService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonalDetailsDTO>> getAll() {
        return ResponseEntity.ok(personalDetailsService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personalDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
