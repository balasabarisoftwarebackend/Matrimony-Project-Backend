package com.example.MatrimonyProject.controller;


import com.example.MatrimonyProject.dto.ReligionDetailsDTO;
import com.example.MatrimonyProject.service.ReligionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/religion-details")
@RequiredArgsConstructor
public class ReligionDetailsController {

    private final ReligionDetailsService religionDetailsService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ReligionDetailsDTO> create(@PathVariable Long userId, @RequestBody ReligionDetailsDTO dto) {
        return ResponseEntity.ok(religionDetailsService.create(userId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReligionDetailsDTO> update(@PathVariable Long id, @RequestBody ReligionDetailsDTO dto) {
        return ResponseEntity.ok(religionDetailsService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReligionDetailsDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(religionDetailsService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReligionDetailsDTO>> getAll() {
        return ResponseEntity.ok(religionDetailsService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        religionDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
