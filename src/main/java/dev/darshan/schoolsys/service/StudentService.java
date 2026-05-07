package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.StudentDto;
import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.enums.StudentStatus;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface StudentService {
    StudentDto createNewStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);

     Void assignSubjectToStudent(Long subjectId, Long studentId);

    @Nullable List<SubjectDto> getAllSubjectsOfStudent(Long studentId);

    void deleteStudentById(Long studentId);

    void unenrollStudentFromSubject(Long studentId, Long subjectId);

    List<StudentDto> getStudentsByStatus(StudentStatus status);

    List<StudentDto> getAllStudents();
}
