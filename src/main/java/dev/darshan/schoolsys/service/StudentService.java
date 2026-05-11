package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.StudentDto;
import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.dto.TranscriptResponse;
import dev.darshan.schoolsys.enums.StudentStatus;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    StudentDto createNewStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);

     Void assignSubjectToStudent(Long subjectId, Long studentId);

    @Nullable List<SubjectDto> getAllSubjectsOfStudent(Long studentId);

    void deleteStudentById(Long studentId);

    void unenrollStudentFromSubject(Long studentId, Long subjectId);

    Page<StudentDto> getStudentsByStatus(StudentStatus status, int page, int size, String sortBy, String direction);

    Page<StudentDto> getAllStudents(int page, int size, String sortBy, String direction);

    Page<StudentDto> getStudentsByGpaAbove(Double gpa, int page, int size, String sortBy, String direction);

    List<StudentDto> getTopStudentsByGpa(int limit);

    TranscriptResponse getStudentTranscript(Long studentId);
}
