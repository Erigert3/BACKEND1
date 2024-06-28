package com.hendisantika.springbootrestapipostgresql.repository;

import com.hendisantika.springbootrestapipostgresql.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Developer findByEmail(String email);
}
