package com.example.MatrimonyProject.repo;

import com.example.MatrimonyProject.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {

    boolean existsByMobile(String mobile);
    boolean existsByEmail(String email);

}
