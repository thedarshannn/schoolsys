package dev.darshan.schoolsys.service.impl;

import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.entity.Professor;
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
    public ProfessorDto createNewProfessor(ProfessorDto dto) {
        Professor professor = professorMapper.toProfessor(dto);
        // id is null here — that's correct
        Professor saved = professorRepository.save(professor);
        // id is generated here by DB
        return professorMapper.toProfessorDto(saved);
    }

    @Override
    public ProfessorDto getProfessorById(Long id) {
        return professorMapper.toProfessorDto(professorRepository.getProfessorById(id));
    }

    @Override
    public ProfessorDto updateProfessor(Long id, ProfessorDto professorDto) {
        Professor existing = professorRepository.findById(id).orElseThrow();

        professorMapper.updateProfessorFromDto(professorDto, existing);

        Professor saved = professorRepository.save(existing);
        return professorMapper.toProfessorDto(saved);
    }

    @Override
    public Void deleteProfessorById(Long id) {
        professorRepository.deleteById(id);
        return null;
    }
}
