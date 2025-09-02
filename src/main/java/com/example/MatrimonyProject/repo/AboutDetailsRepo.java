package com.example.MatrimonyProject.repo;

import com.example.MatrimonyProject.model.AboutDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutDetailsRepo extends JpaRepository<AboutDetails, Long> {


}
