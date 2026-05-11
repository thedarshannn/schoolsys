package dev.darshan.schoolsys.dto;


import java.util.List;

public record SubjectCountResponse(
        Long professorId,
        Long subjectCount,
        List<SubjectDto> subjects
) {
}
