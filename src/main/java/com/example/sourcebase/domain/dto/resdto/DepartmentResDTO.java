package com.example.sourcebase.domain.dto.resdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResDTO {
    Long id;
    String name;
    Boolean deleted;
    @JsonIgnoreProperties("department")
    List<CriteriaResDTO> criteria;
}
