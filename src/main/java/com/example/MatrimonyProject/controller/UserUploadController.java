package com.example.MatrimonyProject.controller;

import com.example.MatrimonyProject.dto.UserUploadDTO;
import com.example.MatrimonyProject.service.UserUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-uploads")
@RequiredArgsConstructor
public class UserUploadController {

    private final UserUploadService userUploadService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<UserUploadDTO> create(@PathVariable Long userId, @RequestBody UserUploadDTO dto) {
        return ResponseEntity.ok(userUploadService.create(userId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserUploadDTO> update(@PathVariable Long id, @RequestBody UserUploadDTO dto) {
        return ResponseEntity.ok(userUploadService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserUploadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userUploadService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserUploadDTO>> getAll() {
        return ResponseEntity.ok(userUploadService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userUploadService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
