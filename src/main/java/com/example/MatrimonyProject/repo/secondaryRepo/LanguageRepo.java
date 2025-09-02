package com.example.MatrimonyProject.repo.secondaryRepo;

import com.example.MatrimonyProject.model.secondary.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {

    // 🔹 Spring Data JPA will auto-implement this
    Optional<Language> findByNameIgnoreCase(String name);

}
