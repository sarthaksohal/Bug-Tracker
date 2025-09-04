package com.project.bug_tracking_system.repository;

import com.project.bug_tracking_system.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user u WHERE u.email = :emailId", nativeQuery = true)
    void deleteByEmail(@Param("emailId") String emailId);}