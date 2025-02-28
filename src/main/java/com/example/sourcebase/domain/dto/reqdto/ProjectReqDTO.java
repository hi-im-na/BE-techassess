package com.example.sourcebase.domain.dto.reqdto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProjectReqDTO {
     String name;

     LocalDate startDay;

     Long leaderId;
     LocalDate endDay;
     Long departmentId;
     List<Long> employeeIds;

}