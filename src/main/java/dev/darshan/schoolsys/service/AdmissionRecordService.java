package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.AdmissionRecordDto;
import dev.darshan.schoolsys.enums.FeesStatus;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface AdmissionRecordService {
    AdmissionRecordDto addAdmissionRecord(AdmissionRecordDto recordDto);

    List<AdmissionRecordDto> getAdmissionsByFeesStatus(FeesStatus feesStatus);

    List<AdmissionRecordDto> getAllRecords();
}
