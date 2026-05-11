package dev.darshan.schoolsys.controller;

import dev.darshan.schoolsys.dto.AdmissionRecordDto;
import dev.darshan.schoolsys.enums.FeesStatus;
import dev.darshan.schoolsys.service.AdmissionRecordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admissions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdmissionRecordController {

    AdmissionRecordService admissionRecordService;

    @PostMapping
    public ResponseEntity<AdmissionRecordDto> addAdmissionRecord(@RequestBody AdmissionRecordDto recordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                admissionRecordService.addAdmissionRecord(recordDto)
        );
    }

    @GetMapping
    public ResponseEntity<List<AdmissionRecordDto>> getAllRecords(
            @RequestParam(required = false) FeesStatus feesStatus
    ){
        if (feesStatus != null){
            return ResponseEntity.ok(admissionRecordService.getAdmissionsByFeesStatus(feesStatus));
        }

        return ResponseEntity.ok(admissionRecordService.getAllRecords());
    }

}
