package com.example.MatrimonyProject.repo;

import com.example.MatrimonyProject.model.ReligionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReligionDetailsRepo extends JpaRepository<ReligionDetails, Long> {

}
