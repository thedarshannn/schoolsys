package dev.darshan.schoolsys.dto;

import dev.darshan.schoolsys.entity.AdmissionRecord;
import dev.darshan.schoolsys.enums.FeesStatus;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link AdmissionRecord}
 */
public record AdmissionRecordDto(
        Long id, Double fees,
                                 FeesStatus feesStatus,
                                 LocalDate admissionDate,
        Long studentId,
                                 String grade) implements Serializable {
}