package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.SubjectDto;

import java.util.List;


public interface SubjectService {
    SubjectDto createNewSubject(SubjectDto subjectDto);

    List<SubjectDto> getAllSubjects();

    List<SubjectDto> getAllSubjectsBySem(String semester);
}
