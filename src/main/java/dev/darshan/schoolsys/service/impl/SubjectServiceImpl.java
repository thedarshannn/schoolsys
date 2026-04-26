package dev.darshan.schoolsys.service.impl;


import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.entity.Subject;
import dev.darshan.schoolsys.mapper.SubjectMapper;
import dev.darshan.schoolsys.repository.SubjectRepository;
import dev.darshan.schoolsys.service.SubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectServiceImpl implements SubjectService{

    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper;

    @Override
    public SubjectDto createNewSubject(SubjectDto subjectDto) {
        Subject toSave = subjectMapper.toSubject(subjectDto);
        Subject saved = subjectRepository.save(toSave);
        return subjectMapper.toSubjectDto(saved);
    }

}
