package com.example.sourcebase.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "department_criterias", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"department_id", "criterias_id", "question_id"}
        )
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentCriterias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criterias_id", nullable = false)
    Criteria criteria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    Question question;
}
