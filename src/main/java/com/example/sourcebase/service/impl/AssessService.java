package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.UserRole;
import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDTO;
import com.example.sourcebase.domain.enumeration.ETypeAssess;
import com.example.sourcebase.exception.AppException;
import com.example.sourcebase.mapper.AssessDetailMapper;
import com.example.sourcebase.mapper.AssessMapper;
import com.example.sourcebase.mapper.CriteriaMapper;
import com.example.sourcebase.repository.*;
import com.example.sourcebase.service.IAssessService;
import com.example.sourcebase.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AssessService implements IAssessService {
    AssessMapper assessMapper;
    AssessDetailMapper assessDetailMapper;
    CriteriaMapper criteriaResMapper;
    IAssessRepository assessRepository;
    IUserRepository userRepository;
    IAssessDetailRepository assessDetailRepository;
    ICriteriaRepository criteriaRepository;
    IQuestionRepository questionRepository;
    IProjectRepository projectRepository;

    @Override
    @Transactional
    public AssessResDTO updateAssess(AssessReqDTO assessReqDto) {
        User user = userRepository.findById(Long.valueOf(assessReqDto.getUserId()))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ETypeAssess type = determineAssessmentType(assessReqDto, user.getUserRoles());

        Assess assess = assessMapper.toAssess(assessReqDto);
        assess.setAssessmentType(type);
        assess.setAssessmentDate(LocalDate.now());

        Long projectId = Long.valueOf(assessReqDto.getProjectId());
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        assess.setProject(project);

        assess.getAssessDetails().forEach(ad -> {
            ad.setQuestion(questionRepository.findById(ad.getQuestion().getId()).orElse(null));
            ad.setCriteria(criteriaRepository.findById(ad.getCriteria().getId()).orElse(null));
        });

        assessRepository.save(assess);

        return assessMapper.toAssessResDto(assess);
    }

    private ETypeAssess determineAssessmentType(AssessReqDTO assessReqDto, List<UserRole> userRoles) {
        if (assessReqDto.getToUserId().equals(assessReqDto.getUserId())) {
            return ETypeAssess.SELF;
        }
        return userRoles.stream().anyMatch(item -> item.getRole().getName().equalsIgnoreCase("Manager"))
                ? ETypeAssess.MANAGER
                : ETypeAssess.TEAM;
    }

    @Override
    public List<AssessResDTO> getListAssessOfUserId(Long userId) {
        return assessRepository.getListAssessOfUserId(userId).stream()
                .map(assess -> {
                    AssessResDTO assessResDTO = assessMapper.toAssessResDto(assess);
                    assessResDTO.setAssessDetails(assessResDTO.getAssessDetails().stream()
                            .peek(assessDetail -> assessDetail.setAssessId(assessResDTO.getId()))
                            .collect(Collectors.toList()));
                    return assessResDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSubmitForm(Long userId, Long toUserId) {
        return false;
    }

    @Override
    public AssessResDTO getAssess(Long userId,Long projectId) {
        return assessMapper.toAssessResDto(assessRepository.findByToUserIdAndAssessmentTypeAndProjectId(userId, ETypeAssess.SELF,projectId));
    }

    @Override
    public List<AssessResDTO> getListAssessByUserId(Long userId) {
        return assessRepository.getListAssessByUserId(userId).stream()
                .map(assess -> {
                    AssessResDTO assessResDTO = assessMapper.toAssessResDto(assess);
                    assessResDTO.setAssessDetails(assessResDTO.getAssessDetails().stream()
                            .peek(assessDetail -> assessDetail.setAssessId(assessResDTO.getId()))
                            .collect(Collectors.toList()));
                    return assessResDTO;
                })
                .collect(Collectors.toList());
    }


}
