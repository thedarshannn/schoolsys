package dev.darshan.schoolsys.service.impl;

import dev.darshan.schoolsys.advice.exception.BusinessException;
import dev.darshan.schoolsys.dto.AdmissionRecordDto;
import dev.darshan.schoolsys.entity.AdmissionRecord;
import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.enums.FeesStatus;
import dev.darshan.schoolsys.mapper.AdmissionRecordMapper;
import dev.darshan.schoolsys.repository.AdmissionRecordRepository;
import dev.darshan.schoolsys.repository.StudentRepository;
import dev.darshan.schoolsys.service.AdmissionRecordService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdmissionRecordServiceImpl implements AdmissionRecordService {

    AdmissionRecordRepository recordRepository;
    StudentRepository studentRepository;
    AdmissionRecordMapper recordMapper;

    @Override
    @Transactional
    public AdmissionRecordDto addAdmissionRecord(AdmissionRecordDto recordDto) {

        System.out.println("studentId received: " + recordDto.studentId()); // debug
        Student student = studentRepository.findById(recordDto.studentId()).orElseThrow();

        if (student.getAdmissionRecord() != null){
            throw new BusinessException("Student with id "+student.getId()+",already has an admission record");
        }

        AdmissionRecord admissionRecord = recordMapper.toAdmissionRecord(recordDto);
        admissionRecord.setStudent(student);
        student.setAdmissionRecord(admissionRecord);

        Student saved = studentRepository.save(student);
        return recordMapper.toAdmissionRecordDto(saved.getAdmissionRecord());
    }

    @Override
    public List<AdmissionRecordDto> getAdmissionsByFeesStatus(FeesStatus feesStatus) {
        return recordRepository.findAdmissionRecordsByFeesStatus(feesStatus).stream().map(recordMapper::toAdmissionRecordDto).collect(Collectors.toList());
    }

    @Override
    public List<AdmissionRecordDto> getAllRecords() {
        return recordRepository.findAll().stream().map(recordMapper::toAdmissionRecordDto).collect(Collectors.toList());
    }
}
