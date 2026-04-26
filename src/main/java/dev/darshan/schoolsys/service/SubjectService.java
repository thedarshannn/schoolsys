package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.SubjectDto;
import org.jspecify.annotations.Nullable;


public interface SubjectService {
    SubjectDto createNewSubject(SubjectDto subjectDto);

}
