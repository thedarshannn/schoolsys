package dev.darshan.schoolsys.service.impl;


import dev.darshan.schoolsys.dto.AvailableSubjectResponse;
import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.entity.Subject;
import dev.darshan.schoolsys.mapper.SubjectMapper;
import dev.darshan.schoolsys.repository.SubjectRepository;
import dev.darshan.schoolsys.service.SubjectService;
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
public class SubjectServiceImpl implements SubjectService{

    private static final Set<String> ALLOWED_SORT_FIELDS =
            Set.of("id", "title", "credits", "maxCapacity", "semester");

    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper;

    @Override
    public SubjectDto createNewSubject(SubjectDto subjectDto) {
        Subject toSave = subjectMapper.toSubject(subjectDto);
        Subject saved = subjectRepository.save(toSave);
        return subjectMapper.toSubjectDto(saved);
    }

    @Override
    public Page<SubjectDto> getAllSubjects(int page, int size, String sortBy, String direction) {
        PageRequest pageable = PageRequest.of(page, size, buildSort(sortBy, direction));
        return subjectRepository.findAll(pageable).map(subjectMapper::toSubjectDto);
    }

    @Override
    public Page<SubjectDto> getAllSubjectsBySem(String semester, int page, int size, String sortBy, String direction) {
        PageRequest pageable = PageRequest.of(page, size, buildSort(sortBy, direction));
        return subjectRepository.getSubjectsBySemester(semester, pageable)
                .map(subjectMapper::toSubjectDto);
    }

    private Sort buildSort(String sortBy, String direction) {
        String field = ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : "id";
        return direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();
    }

    @Override
    public List<AvailableSubjectResponse> getAvailableSubjects() {
        return subjectRepository.findAvailableSubjects()
                .stream()
                .map(subject -> new AvailableSubjectResponse(
                        subject.getId(),
                        subject.getTitle(),
                        subject.getCourseCode(),
                        subject.getSemester(),
                        subject.getMaxCapacity(),
                        subject.getStudents().size(),
                        subject.getMaxCapacity() - subject.getStudents().size()
                ))
                .toList();
    }

}
