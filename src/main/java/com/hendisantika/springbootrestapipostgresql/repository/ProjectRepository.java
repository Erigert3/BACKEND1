package com.hendisantika.springbootrestapipostgresql.repository;

import com.hendisantika.springbootrestapipostgresql.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
