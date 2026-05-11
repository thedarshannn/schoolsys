package dev.darshan.schoolsys.dto;

public record DepartmentSummaryResponse(
        String department,
        Long professorCount,
        Long studentCount,
        Long subjectCount
) {}