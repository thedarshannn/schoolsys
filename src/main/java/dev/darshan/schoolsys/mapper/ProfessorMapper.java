package dev.darshan.schoolsys.mapper;

import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.entity.Professor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    ProfessorDto toProfessorDto(Professor professor);
    Professor toProfessor(ProfessorDto professorDto);
}
