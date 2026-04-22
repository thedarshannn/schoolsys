package dev.darshan.schoolsys.dto;

import java.io.Serializable;

/**
 * DTO for {@link dev.darshan.schoolsys.entity.Subject}
 */
public record SubjectDto(
        Long id, String title,
                         String courseCode,
                         Integer credits,
                         Integer maxCapacity,
                         String semester) implements Serializable {
}