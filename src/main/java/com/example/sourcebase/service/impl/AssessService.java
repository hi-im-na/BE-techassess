package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
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
    UserService userService;

    @Override
    @Transactional
    public AssessResDTO updateAssess(AssessReqDTO assessReqDto) {
        User user = userRepository.findById(Long.valueOf(assessReqDto.getUserId()))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        User toUser = userRepository.findById(Long.valueOf(assessReqDto.getToUserId()))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Project project = projectRepository.findById(Long.valueOf(assessReqDto.getProjectId()))
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        ETypeAssess type = determineAssessmentType(assessReqDto, project.getLeader().getId());

        // nếu là leader đánh giá, kiểm tra thaành viên trong project đã đánh giá chưa
        if (type.equals(ETypeAssess.MANAGER)) {
            if (!assessRepository.existsByToUser_IdAndProject_IdAndAssessmentType(toUser.getId(), project.getId(), ETypeAssess.SELF)) {
                throw new AppException(ErrorCode.SELF_ASSESS_IS_NOT_EXIST);
            }

            if (!assessRepository.existsByToUser_IdAndProject_IdAndAssessmentType(toUser.getId(), project.getId(), ETypeAssess.TEAM)) {
                throw new AppException(ErrorCode.TEAM_ASSESS_IS_NOT_EXIST);
            } else {
                int numberOfAssessOfTeamMember = assessRepository.countByToUser_IdAndProject_IdAndAssessmentType(toUser.getId(), project.getId(), ETypeAssess.TEAM);
                if (numberOfAssessOfTeamMember < userService.getAllUserHadSameProject(toUser.getId(), project.getId()).size() - 1) {
                    throw new AppException(ErrorCode.TEAM_ASSESS_NOT_FULFILLED);
                }
            }
        }

        Assess assess = assessMapper.toAssess(assessReqDto);
        assess.setAssessmentType(type);
        assess.setAssessmentDate(LocalDate.now());

        assess.getAssessDetails().forEach(ad -> {
            ad.setQuestion(questionRepository.findById(ad.getQuestion().getId()).orElse(null));
            ad.setCriteria(criteriaRepository.findById(ad.getCriteria().getId()).orElse(null));
        });

        assessRepository.save(assess);

        return assessMapper.toAssessResDto(assess);
    }

    private ETypeAssess determineAssessmentType(AssessReqDTO assessReqDto, Long leaderId) {
        if (assessReqDto.getToUserId().equals(assessReqDto.getUserId())) {
            return ETypeAssess.SELF;
        }
        return leaderId.equals(Long.valueOf(assessReqDto.getUserId()))
                ? ETypeAssess.MANAGER
                : ETypeAssess.TEAM;
    }

    @Override
    public List<AssessResDTO> getListAssessOfUserId(Long userId, Long projectId) {
        return assessRepository.findByToUser_IdAndProject_Id(userId, projectId).stream()
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
    public AssessResDTO getAssess(Long userId, Long projectId) {
        return assessMapper.toAssessResDto(assessRepository.findByToUserIdAndAssessmentTypeAndProjectId(userId, ETypeAssess.SELF, projectId));
    }

    @Override
    public List<AssessResDTO> getListAssessByUserId(Long userId,Long projectId) {
        return assessRepository.findByUser_IdAndProject_Id(userId, projectId).stream()
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
