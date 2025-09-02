package com.example.MatrimonyProject.controller;

import com.example.MatrimonyProject.dto.UserProfileDTO;
import com.example.MatrimonyProject.utilits.UserProfileSearchRequest;
import com.example.MatrimonyProject.service.UserProfileService;
import com.example.MatrimonyProject.utilits.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
@Validated
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfileDTO> create(@RequestBody UserProfileDTO dto) {
        return ResponseEntity.ok(userProfileService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> update(@PathVariable Long id, @RequestBody UserProfileDTO dto) {
        return ResponseEntity.ok(userProfileService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserProfileDTO>> getAll() {
        return ResponseEntity.ok(userProfileService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userProfileService.delete(id);
        return ResponseEntity.noContent().build();
    }


    // -------- GET ALL (Paginated) ----------
    @GetMapping("/paged")
    public ResponseEntity<PageResponse<UserProfileDTO>> getAll(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        // Wrap manually into search request with no filters
        UserProfileSearchRequest emptyReq = UserProfileSearchRequest.builder().build();
        PageResponse<UserProfileDTO> resp = userProfileService.search(emptyReq, pageable);

        return ResponseEntity.ok(resp);
    }

    // -------- SEARCH (with filters + pagination) ----------
    @GetMapping("/search")
    public ResponseEntity<PageResponse<UserProfileDTO>> search(
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String religion,
            @RequestParam(required = false) String caste,
            @RequestParam(required = false) String subCaste,
            @RequestParam(required = false) String maritalStatus,
            @RequestParam(required = false) String familyStatus,
            @RequestParam(required = false) String familyType,
            @RequestParam(required = false) String education,
            @RequestParam(required = false) String occupation,
            @RequestParam(required = false) Long annualIncomeMin,
            @RequestParam(required = false) Long annualIncomeMax,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        UserProfileSearchRequest req = UserProfileSearchRequest.builder()
                .minAge(minAge).maxAge(maxAge).gender(gender)
                .religion(religion).caste(caste).subCaste(subCaste)
                .maritalStatus(maritalStatus).familyStatus(familyStatus).familyType(familyType)
                .education(education).occupation(occupation)
                .annualIncomeMin(annualIncomeMin).annualIncomeMax(annualIncomeMax)
                .city(city).state(state).country(country)
                .q(q)
                .build();

        PageResponse<UserProfileDTO> resp = userProfileService.search(req, pageable);
        return ResponseEntity.ok(resp);
    }


}
