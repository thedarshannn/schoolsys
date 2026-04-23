package dev.darshan.schoolsys.service;

import dev.darshan.schoolsys.dto.AdmissionRecordDto;
import org.jspecify.annotations.Nullable;

public interface AdmissionRecordService {
    AdmissionRecordDto addAdmissionRecord(AdmissionRecordDto recordDto);
}
