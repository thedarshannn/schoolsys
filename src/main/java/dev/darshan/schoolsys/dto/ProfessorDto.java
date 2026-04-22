package dev.darshan.schoolsys.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.darshan.schoolsys.entity.Professor}
 */
public record ProfessorDto(String firstName,
                           String lastName,
                           String title,
                           String email,
                           String department,
                           Double salary,
                           LocalDate hireDate) implements Serializable {
}