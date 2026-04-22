package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.StudentDto;
import org.jspecify.annotations.Nullable;

public interface StudentService {
    StudentDto createNewStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);
}
