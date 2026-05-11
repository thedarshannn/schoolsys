package dev.darshan.schoolsys.service.impl;

import dev.darshan.schoolsys.advice.exception.BusinessException;
import dev.darshan.schoolsys.advice.exception.ResourceNotFoundException;
import dev.darshan.schoolsys.dto.StudentDto;
import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.entity.Subject;
import dev.darshan.schoolsys.enums.StudentStatus;
import dev.darshan.schoolsys.mapper.StudentMapper;
import dev.darshan.schoolsys.mapper.SubjectMapper;
import dev.darshan.schoolsys.repository.StudentRepository;
import dev.darshan.schoolsys.repository.SubjectRepository;
import dev.darshan.schoolsys.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    SubjectRepository subjectRepository;
    StudentMapper studentMapper;
    SubjectMapper subjectMapper;

    @Override
    public StudentDto createNewStudent(StudentDto studentDto) {
        Student newStudent = studentMapper.toStudent(studentDto);
        Student saved = studentRepository.save(newStudent);
        return studentMapper.toStudentDto(saved);
    }

    @Override
    public StudentDto getStudentById(Long id) {

        return studentMapper.toStudentDto(studentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Student Not Found With id: "+id)));
    }

    @Override
    @Transactional
    public Void assignSubjectToStudent(Long subjectId, Long studentId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        if (student.getSubjects().contains(subject)) {
            throw new BusinessException("Student is already enrolled in this subject");
        }

        student.getSubjects().add(subject);   // owning side — has @JoinTable
        subject.getStudents().add(student);   // inverse side — keep in-memory in sync

        studentRepository.save(student);      // save owning side

        return null;
    }

    @Override
    public List<SubjectDto> getAllSubjectsOfStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Student not found with id: " + studentId));

        return  student.getSubjects().stream()
                .map(subjectMapper::toSubjectDto)
                .toList();
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public void unenrollStudentFromSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + studentId));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subject not found with id: " + subjectId));

        if (!student.getSubjects().contains(subject)) {
            throw new BusinessException("Student is not enrolled in this subject");
        }

        student.getSubjects().remove(subject);    // remove from owning side
        subject.getStudents().remove(student);    // keep inverse in sync

        studentRepository.save(student);
    }

    @Override
    public List<StudentDto> getStudentsByStatus(StudentStatus status) {
        return studentRepository.findByStatus(status)
                .stream()
                .map(studentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsByGpaAbove(Double gpa) {
        return studentRepository.findByGpaGreaterThanEqual(gpa)
                .stream()
                .map(studentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getTopStudentsByGpa(int limit) {
        return studentRepository.findTopStudentsByGpa(limit)
                .stream()
                .map(studentMapper::toStudentDto)
                .toList();
    }
}
