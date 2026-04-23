package dev.darshan.schoolsys.mapper;


import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.entity.Subject;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDto toSubjectDto(Subject subject);
    Subject toSubject(SubjectDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubjectFromDto(SubjectDto dto, @MappingTarget Subject student);
}
