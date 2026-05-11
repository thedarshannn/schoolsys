package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.DepartmentSummaryResponse;
import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.dto.StudentDto;
import dev.darshan.schoolsys.dto.SubjectCountResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfessorService {

    ProfessorDto createNewProfessor(ProfessorDto professorDto);
    ProfessorDto getProfessorById(Long id);

    ProfessorDto updateProfessor(Long id, ProfessorDto professorDto);

    Void deleteProfessorById(Long id);

    Void assignSubject(Long id, Long subjectId);

    Void assignStudent(Long profId, Long studentId);

    List<StudentDto> getAllStudentsOfProf(Long profId);

    Page<ProfessorDto> getProfessorsByDepartment(String department, int page, int size, String sortBy, String direction);

    Page<ProfessorDto> getAllProfessors(int page, int size, String sortBy, String direction);

    SubjectCountResponse getSubjectCountForProfessor(Long professorId);

    List<DepartmentSummaryResponse> getDepartmentSummary();
}
