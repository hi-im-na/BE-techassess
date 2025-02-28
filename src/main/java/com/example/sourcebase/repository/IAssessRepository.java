package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.enumeration.ETypeAssess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAssessRepository extends JpaRepository<Assess, Long> {

    @Query("SELECT a FROM Assess a WHERE a.toUser.id = :userId")
    List<Assess> getListAssessOfUserId(Long userId);

//    Assess findByToUserIdAndAssessmentType(Long userId, ETypeAssess type);

    Assess findByToUserIdAndAssessmentTypeAndProjectId(Long toUserId, ETypeAssess assessmentType, Long projectId);

    @Query("SELECT a FROM Assess a WHERE a.user.id = :userId")
    List<Assess> getListAssessByUserId(Long userId);

    @Query("SELECT a FROM Assess a WHERE a.toUser.id = :toUserId AND a.assessmentType = 'SELF'")
    Assess getAssessBySelf(Long toUserId);

    @Query("SELECT a FROM Assess a WHERE a.toUser.id = :toUserId AND a.assessmentType = 'TEAM'")
    List<Assess> getListAssessTeamOfUserId(Long toUserId);

    // find all assess by toUser id and project id and assessment type
    List<Assess> findAllByToUser_IdAndProject_IdAndAssessmentType(Long toUserId, Long projectId, ETypeAssess assessmentType);

    /**
     * find all assess by toUser id
     *
     * @param userId person being rated
     * @return list of assess
     */
    List<Assess> findByToUser_Id(Long userId);

}