package com.example.MatrimonyProject.repo;

import com.example.MatrimonyProject.model.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDetailsRepo extends JpaRepository<PersonalDetails, Long> {


}
