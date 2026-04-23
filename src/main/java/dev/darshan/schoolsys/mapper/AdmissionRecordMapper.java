package dev.darshan.schoolsys.mapper;

import dev.darshan.schoolsys.dto.AdmissionRecordDto;
import dev.darshan.schoolsys.entity.AdmissionRecord;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdmissionRecordMapper {

    @Mapping(target = "student", ignore = true)
    AdmissionRecord toAdmissionRecord(AdmissionRecordDto record);

    @Mapping(target = "studentId", source = "student.id")
    AdmissionRecordDto toAdmissionRecordDto(AdmissionRecord record);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdmissionRecordFromDto(AdmissionRecordDto dto, @MappingTarget AdmissionRecord record);
}
