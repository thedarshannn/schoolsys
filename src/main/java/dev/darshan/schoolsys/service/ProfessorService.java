package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.dto.StudentDto;

import java.util.List;

public interface ProfessorService {

    ProfessorDto createNewProfessor(ProfessorDto professorDto);
    ProfessorDto getProfessorById(Long id);

    ProfessorDto updateProfessor(Long id, ProfessorDto professorDto);

    Void deleteProfessorById(Long id);

    Void assignSubject(Long id, Long subjectId);

    Void assignStudent(Long profId, Long studentId);

    List<StudentDto> getAllStudentsOfProf(Long profId);
}
