package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.UserProject;
import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserProjectResDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "startDay", target = "startDay")
    @Mapping(source = "endDay", target = "endDay")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(target = "user.id", source = "leaderId")
    Project toEntity(ProjectReqDTO dto);


    ProjectResDTO toResponseDTO(Project entity);
    List<ProjectResDTO> toProjectResDTOs(List<Project> projects);


    List<UserResDTO> toUserDTOs(List<User> users);
    List<UserProjectResDTO> toUserProjectResDTOs(List<UserProject> userProjects);

}
