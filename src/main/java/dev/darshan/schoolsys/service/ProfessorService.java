package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.ProfessorDto;
import org.jspecify.annotations.Nullable;

public interface ProfessorService {

    ProfessorDto createNewProfessor(ProfessorDto professorDto);
    ProfessorDto getProfessorById(Long id);

    ProfessorDto updateProfessor(Long id, ProfessorDto professorDto);

    Void deleteProfessorById(Long id);

    Void assignSubject(Long id, Long subjectId);

    Void assignStudent(Long profTd, Long studentId);
}
