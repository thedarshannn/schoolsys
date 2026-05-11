package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.entity.AdmissionRecord;
import dev.darshan.schoolsys.enums.FeesStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord, Long> {

    List<AdmissionRecord> findAdmissionRecordsByFeesStatus(FeesStatus feesStatus);
}