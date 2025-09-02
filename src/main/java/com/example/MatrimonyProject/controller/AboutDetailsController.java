package com.example.MatrimonyProject.controller;

import com.example.MatrimonyProject.dto.AboutDetailsDTO;
import com.example.MatrimonyProject.service.AboutDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/about-details")
@RequiredArgsConstructor
public class AboutDetailsController {


    private final AboutDetailsService aboutDetailsService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<AboutDetailsDTO> create(@PathVariable Long userId, @RequestBody AboutDetailsDTO dto) {
        return ResponseEntity.ok(aboutDetailsService.create(userId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AboutDetailsDTO> update(@PathVariable Long id, @RequestBody AboutDetailsDTO dto) {
        return ResponseEntity.ok(aboutDetailsService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AboutDetailsDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(aboutDetailsService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AboutDetailsDTO>> getAll() {
        return ResponseEntity.ok(aboutDetailsService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        aboutDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
