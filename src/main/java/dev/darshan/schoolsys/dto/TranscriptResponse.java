package dev.darshan.schoolsys.dto;

import java.util.List;

public record TranscriptResponse(
        StudentDto student,
        List<ProfessorDto> professors,
        List<SubjectDto> subjects,
        AdmissionRecordDto admissionRecord
) {
}
