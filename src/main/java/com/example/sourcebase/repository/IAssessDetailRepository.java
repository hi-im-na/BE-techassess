package com.example.sourcebase.repository;

import com.example.sourcebase.domain.AssessDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IAssessDetailRepository extends JpaRepository<AssessDetail, Long> {

    List<AssessDetail> findByAssess_Id(Long assessId);

    List<AssessDetail> findByAssess_IdAndAssess_AssessmentDate(Long assessId, LocalDate assessmentDate);
}