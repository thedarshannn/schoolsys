package dev.darshan.schoolsys.service.impl;

import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.mapper.ProfessorMapper;
import dev.darshan.schoolsys.repository.ProfessorRepository;
import dev.darshan.schoolsys.service.ProfessorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessorServiceImpl implements ProfessorService {

    ProfessorRepository professorRepository;
    ProfessorMapper professorMapper;

    @Override
    public ProfessorDto createNewProfessor(ProfessorDto professorDto) {
        professorRepository.save(professorMapper.toProfessor(professorDto));
        return professorDto;
    }

    @Override
    public ProfessorDto getProfessorById(Long id) {
        return professorMapper.toProfessorDto(professorRepository.getProfessorById(id));
    }
}
