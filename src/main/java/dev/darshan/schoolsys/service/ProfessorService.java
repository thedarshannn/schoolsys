package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.ProfessorDto;

public interface ProfessorService {

    ProfessorDto createNewProfessor(ProfessorDto professorDto);
    ProfessorDto getProfessorById(Long id);
}
