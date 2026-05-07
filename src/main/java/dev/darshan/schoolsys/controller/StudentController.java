package dev.darshan.schoolsys.controller;

import dev.darshan.schoolsys.dto.StudentDto;

import dev.darshan.schoolsys.dto.SubjectDto;
import dev.darshan.schoolsys.enums.StudentStatus;
import dev.darshan.schoolsys.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    StudentService service;

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.createNewStudent(studentDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(
                service.getStudentById(id)
        );
    }

    @PutMapping("/{subjectId}/enroll/{studentId}")
    public ResponseEntity<Void> assigntheSubjectToStudent(@PathVariable Long subjectId, @PathVariable Long studentId){
        return ResponseEntity.ok(service.assignSubjectToStudent(subjectId, studentId));
    }

    @GetMapping("/{studentId}/subjects")
    public ResponseEntity<List<SubjectDto>> getAllSubjectsOfStudent(@PathVariable Long studentId){
        return ResponseEntity.ok(service.getAllSubjectsOfStudent(studentId));
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable Long studentId){
        service.deleteStudentById(studentId);
    }

    @DeleteMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<Void> unenrollFromSubject(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {
        service.unenrollStudentFromSubject(studentId, subjectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(
            @RequestParam(required = false) StudentStatus status) {

        if (status != null) {
            return ResponseEntity.ok(service.getStudentsByStatus(status));
        }
        return ResponseEntity.ok(service.getAllStudents());
    }
}
