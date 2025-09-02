package com.example.MatrimonyProject.repo;

import com.example.MatrimonyProject.model.ProfessionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalDetailsRepo extends JpaRepository<ProfessionalDetails, Long> {


}
