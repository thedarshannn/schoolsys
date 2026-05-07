package dev.darshan.schoolsys.service.impl;

import dev.darshan.schoolsys.advice.exception.BusinessException;
import dev.darshan.schoolsys.advice.exception.ResourceNotFoundException;
import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.dto.StudentDto;
import dev.darshan.schoolsys.entity.Professor;
import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.entity.Subject;
import dev.darshan.schoolsys.mapper.ProfessorMapper;
import dev.darshan.schoolsys.mapper.StudentMapper;
import dev.darshan.schoolsys.repository.ProfessorRepository;
import dev.darshan.schoolsys.repository.StudentRepository;
import dev.darshan.schoolsys.repository.SubjectRepository;
import dev.darshan.schoolsys.service.ProfessorService;
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
public class ProfessorServiceImpl implements ProfessorService {

    ProfessorRepository professorRepository;
    SubjectRepository subjectRepository;
    ProfessorMapper professorMapper;
    StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public ProfessorDto createNewProfessor(ProfessorDto dto) {
        Professor professor = professorMapper.toProfessor(dto);
        // id is null here — that's correct
        Professor saved = professorRepository.save(professor);
        // id is generated here by DB
        return professorMapper.toProfessorDto(saved);
    }

    @Override
    public ProfessorDto getProfessorById(Long id) {
        return professorMapper.toProfessorDto(professorRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Professor not found with id: " + id)));
    }

    @Override
    public ProfessorDto updateProfessor(Long id, ProfessorDto professorDto) {
        Professor existing = professorRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Professor not found with id: " + id));

        professorMapper.updateProfessorFromDto(professorDto, existing);

        Professor saved = professorRepository.save(existing);
        return professorMapper.toProfessorDto(saved);
    }

    @Override
    public Void deleteProfessorById(Long id) {
        professorRepository.deleteById(id);
        return null;
    }

    @Override
    @Transactional
    public Void assignSubject(Long id, Long subjectId) {

        Professor professor = professorRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Professor not found with id: " + id));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Subject not found with id: " + id));

        if (professor.getSubjects().contains(subject)) {
            throw new BusinessException("Subject is already assigned to professor with id "+id);
        }

        subject.setProfessor(professor);         // ManyToOne side — sets FK column
        professor.getSubjects().add(subject);    // OneToMany side — keeps in-memory in sync

        subjectRepository.save(subject);// save the owning side (has the FK)

        return null;
    }

    @Override
    @Transactional
    public Void assignStudent(Long profId, Long studentId) {

        Professor professor = professorRepository.findById(profId)
                .orElseThrow(()-> new ResourceNotFoundException("Professor not found with id: " + profId));


        Student student = studentRepository.findById(profId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found with id: " + profId));

        if (student.getProfessors().contains(professor)){
            throw new BusinessException("Professor is already assigned to this student!");
        }

        professor.getStudents().add(student);
        student.getProfessors().add(professor);

        professorRepository.save(professor);

        return null;
    }

    @Override
    public List<StudentDto> getAllStudentsOfProf(Long profId) {
        Professor professor = professorRepository.findById(profId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Professor not found with id: " + profId));

        return professor.getStudents().stream()
                .map(studentMapper::toStudentDto)
                .toList();
    }

    @Override
    public List<ProfessorDto> getProfessorsByDepartment(String department) {
        return professorRepository.findByDepartment(department)
                .stream()
                .map(professorMapper::toProfessorDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfessorDto> getAllProfessors() {
        return professorRepository.findAll().stream().map(professorMapper::toProfessorDto).collect(Collectors.toList());
    }
}
