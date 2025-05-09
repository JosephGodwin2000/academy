package com.hcl.academy.repository;

import com.hcl.academy.entity.Users;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);

    boolean existsByContactNumber(String contactNumber);

    @Query(value = "SELECT * FROM users order by associate_id desc limit 1", nativeQuery = true)
    Optional<Users> findLatestAssociateId();
}
