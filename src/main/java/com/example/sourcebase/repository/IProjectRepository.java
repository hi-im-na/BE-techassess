package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<Project,Long> {
    boolean existsByName(String name);
}


