package dev.darshan.schoolsys.dto;

public record AvailableSubjectResponse(
        Long id,
        String title,
        String courseCode,
        String semester,
        Integer maxCapacity,
        Integer enrolledCount,
        Integer spotsRemaining
) {}