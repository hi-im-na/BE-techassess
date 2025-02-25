package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Answer;
import com.example.sourcebase.domain.Criteria;
import com.example.sourcebase.domain.Department;
import com.example.sourcebase.domain.dto.reqdto.DepartmentReqDTO;
import com.example.sourcebase.domain.dto.resdto.AnswerResDTO;
import com.example.sourcebase.domain.dto.resdto.CriteriaResDTO;
import com.example.sourcebase.domain.dto.resdto.DepartmentResDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department toEntity(DepartmentReqDTO reqDTO);

    Department toEntity(DepartmentResDTO resDTO);

    @Mapping(target = "criteria", source = "criterias")
    DepartmentResDTO toDepartmentResDTO(Department department);

    @Mapping(target = "department", ignore = true)
    CriteriaResDTO toCriteriaResDTO(Criteria criteria);

    AnswerResDTO toAnswerResDTO(Answer answer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department partialUpdate(DepartmentReqDTO reqDTO, @MappingTarget Department department);
}
