package dev.darshan.schoolsys.controller;

import dev.darshan.schoolsys.dto.AvailableSubjectResponse;
import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.service.SubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<Page<SubjectDto>> getAllSubjects(
            @RequestParam(required = false) String semester,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        if (semester != null) {
            return ResponseEntity.ok(subjectService.getAllSubjectsBySem(semester, page, size, sortBy, direction));
        }
        return ResponseEntity.ok(subjectService.getAllSubjects(page, size, sortBy, direction));
    }

    @GetMapping("/available")
    public ResponseEntity<List<AvailableSubjectResponse>> getAvailableSubjects() {
        return ResponseEntity.ok(subjectService.getAvailableSubjects());
    }

}
