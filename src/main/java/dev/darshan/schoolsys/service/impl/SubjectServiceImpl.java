package dev.darshan.schoolsys.service.impl;

import dev.darshan.schoolsys.advice.exception.BusinessException;
import dev.darshan.schoolsys.advice.exception.ResourceNotFoundException;
import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.entity.Subject;
import dev.darshan.schoolsys.mapper.SubjectMapper;
import dev.darshan.schoolsys.repository.StudentRepository;
import dev.darshan.schoolsys.repository.SubjectRepository;
import dev.darshan.schoolsys.service.SubjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectServiceImpl implements SubjectService{

    SubjectRepository subjectRepository;
    StudentRepository studentRepository;
    SubjectMapper subjectMapper;

    @Override
    public SubjectDto createNewSubject(SubjectDto subjectDto) {
        Subject toSave = subjectMapper.toSubject(subjectDto);
        Subject saved = subjectRepository.save(toSave);
        return subjectMapper.toSubjectDto(saved);
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
}
