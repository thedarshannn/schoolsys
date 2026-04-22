package dev.darshan.schoolsys.mapper;


import dev.darshan.schoolsys.dto.StudentDto;
import dev.darshan.schoolsys.entity.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toStudent(StudentDto dto);
    StudentDto toStudentDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(StudentDto dto, @MappingTarget Student student);
}
