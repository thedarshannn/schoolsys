package dev.darshan.schoolsys.service.impl;

import dev.darshan.schoolsys.dto.StudentDto;
import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.mapper.StudentMapper;
import dev.darshan.schoolsys.repository.StudentRepository;
import dev.darshan.schoolsys.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    StudentMapper studentMapper;

    @Override
    public StudentDto createNewStudent(StudentDto studentDto) {
        Student newStudent = studentMapper.toStudent(studentDto);
        Student saved = studentRepository.save(newStudent);
        return studentMapper.toStudentDto(saved);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return studentMapper.toStudentDto(studentRepository.findById(id).orElseThrow());
    }
}
