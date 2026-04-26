package dev.darshan.schoolsys.controller;

import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.service.SubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subjects")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SubjectController {

    SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectDto> createNewSubject(@RequestBody SubjectDto subjectDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                subjectService.createNewSubject(subjectDto)
        );
    }

//    @PutMapping("/{subjectId}/enroll/{studentId}")
//    public ResponseEntity<Void> assigntheSubjectToStudent(@PathVariable Long subjectId, @PathVariable Long studentId){
//         return ResponseEntity.ok(subjectService.assignSubjectToStudent(subjectId, studentId));
//    }

}
