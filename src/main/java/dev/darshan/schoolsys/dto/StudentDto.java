package dev.darshan.schoolsys.dto;

import dev.darshan.schoolsys.enums.StudentStatus;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link dev.darshan.schoolsys.entity.Student}
 */
public record StudentDto(String name,
                         String email,
                         LocalDate enrollmentDate,
                         Double gpa,
                         StudentStatus status) implements Serializable {
}