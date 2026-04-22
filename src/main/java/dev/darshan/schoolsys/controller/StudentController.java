package dev.darshan.schoolsys.controller;

import dev.darshan.schoolsys.dto.StudentDto;

import dev.darshan.schoolsys.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
