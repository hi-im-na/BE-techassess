package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.CriteriaReqDTO;
import com.example.sourcebase.domain.dto.resdto.CriteriaResDTO;
import com.example.sourcebase.domain.dto.resdto.QuestionResDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICriteriaService {
    List<CriteriaResDTO> getAllCriterias();

    Page<CriteriaResDTO> getAllCriteria(int page, int size, String sortBy, boolean asc);

    CriteriaResDTO getCriteriaById(Long id);

    CriteriaResDTO addCriterion(CriteriaReqDTO criteriaReqDTO);

    CriteriaResDTO updateCriterion(Long id, CriteriaReqDTO criteriaReqDTO);

    void deleteCriterion(Long id);

    void validateUniqueTitle(CriteriaReqDTO criteriaReqDTO);

    Page<QuestionResDTO> findQuestionsByCriterionId(Long criteriaId, int page, int size, String sortBy, boolean asc);

    CriteriaResDTO addCriterionToDepartment(CriteriaReqDTO criteriaReqDTO, Long departmentId);

    CriteriaResDTO updateCriterionInDepartment(CriteriaReqDTO criteriaReqDTO, Long departmentId, Long criteriaId);

}
