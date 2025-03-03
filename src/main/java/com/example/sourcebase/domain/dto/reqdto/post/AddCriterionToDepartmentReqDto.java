package com.example.sourcebase.domain.dto.reqdto.post;

import com.example.sourcebase.domain.dto.reqdto.CriteriaReqDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCriterionToDepartmentReqDto {
    CriteriaReqDTO criteriaReqDTO;
    Long departmentId;
}
