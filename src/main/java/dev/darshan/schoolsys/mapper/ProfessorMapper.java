package dev.darshan.schoolsys.mapper;

import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.entity.Professor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    ProfessorDto toProfessorDto(Professor professor);
    Professor toProfessor(ProfessorDto professorDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfessorFromDto(ProfessorDto dto, @MappingTarget Professor professor);
}
