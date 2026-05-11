package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.AvailableSubjectResponse;
import dev.darshan.schoolsys.dto.SubjectDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface SubjectService {
    SubjectDto createNewSubject(SubjectDto subjectDto);

    Page<SubjectDto> getAllSubjects(int page, int size, String sortBy, String direction);

    Page<SubjectDto> getAllSubjectsBySem(String semester, int page, int size, String sortBy, String direction);

    List<AvailableSubjectResponse> getAvailableSubjects();
}
