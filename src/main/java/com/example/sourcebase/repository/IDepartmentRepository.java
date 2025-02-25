package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Criteria;
import com.example.sourcebase.domain.Department;
import com.example.sourcebase.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByNameIgnoreCaseAndDeletedIsFalse(String name);

    @Override
    @Query("SELECT d FROM Department d WHERE d.deleted = false")
    List<Department> findAll();

    @Override
    @Query("SELECT d FROM Department d WHERE d.deleted = false")
    Page<Department> findAll(Pageable pageable);

    @Override
    @Query("SELECT d FROM Department d WHERE d.id = :id AND d.deleted = false")
    Optional<Department> findById(@Param("id") Long id);

    @Query("SELECT COUNT(d) > 0 FROM Department d WHERE lower(d.name) = lower(:name) AND d.deleted = false")
    boolean existsByNameIgnoreCase(String name);

}
