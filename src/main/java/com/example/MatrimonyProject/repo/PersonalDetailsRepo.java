package com.example.MatrimonyProject.repo;

import com.example.MatrimonyProject.model.PersonalDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalDetailsRepo extends JpaRepository<PersonalDetails, Long> {

    @EntityGraph(attributePaths = {"motherTongue", "languagesKnown"})
    Optional<PersonalDetails> findById(Long aLong);
}
