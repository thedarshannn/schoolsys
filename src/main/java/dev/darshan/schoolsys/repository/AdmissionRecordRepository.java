package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord, Long> {
}