package com.example.MatrimonyProject.repo;

import com.example.MatrimonyProject.model.UserUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUploadRepo extends JpaRepository<UserUpload, Long> {


}
