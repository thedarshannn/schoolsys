package dev.darshan.schoolsys.service.impl;

import dev.darshan.schoolsys.advice.exception.BusinessException;
import dev.darshan.schoolsys.advice.exception.ResourceNotFoundException;
import dev.darshan.schoolsys.dto.*;
import dev.darshan.schoolsys.entity.Professor;
import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.entity.Subject;
import dev.darshan.schoolsys.mapper.ProfessorMapper;
import dev.darshan.schoolsys.mapper.StudentMapper;
import dev.darshan.schoolsys.mapper.SubjectMapper;
import dev.darshan.schoolsys.repository.ProfessorRepository;
import dev.darshan.schoolsys.repository.StudentRepository;
import dev.darshan.schoolsys.repository.SubjectRepository;
import dev.darshan.schoolsys.service.ProfessorService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessorServiceImpl implements ProfessorService {

    private static final Set<String> ALLOWED_SORT_FIELDS =
            Set.of("id", "firstName", "lastName", "salary", "department", "hireDate");

    ProfessorRepository professorRepository;
    SubjectRepository subjectRepository;
    ProfessorMapper professorMapper;
    StudentRepository studentRepository;
    StudentMapper studentMapper;
    SubjectMapper subjectMapper;

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
    public Page<ProfessorDto> getProfessorsByDepartment(String department, int page, int size, String sortBy, String direction) {
        PageRequest pageable = PageRequest.of(page, size, buildSort(sortBy, direction));
        return professorRepository.findByDepartment(department, pageable)
                .map(professorMapper::toProfessorDto);
    }

    @Override
    public Page<ProfessorDto> getAllProfessors(int page, int size, String sortBy, String direction) {
        PageRequest pageable = PageRequest.of(page, size, buildSort(sortBy, direction));
        return professorRepository.findAll(pageable).map(professorMapper::toProfessorDto);
    }

    private Sort buildSort(String sortBy, String direction) {
        String field = ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : "id";
        return direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();
    }

    @Override
    public SubjectCountResponse getSubjectCountForProfessor(Long professorId) {

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + professorId));

        long count = subjectRepository.countSubjectByProfessor_Id(professorId);

        List<SubjectDto> subjects = professor.getSubjects()
                .stream()
                .map(subjectMapper::toSubjectDto)
                .toList();

        return new SubjectCountResponse(professorId, count, subjects);
    }

    @Override
    public List<DepartmentSummaryResponse> getDepartmentSummary() {
        return professorRepository.findDepartmentSummary();
    }
}
