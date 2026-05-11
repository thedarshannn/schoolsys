package dev.darshan.schoolsys.controller;

import dev.darshan.schoolsys.dto.DepartmentSummaryResponse;
import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.dto.StudentDto;
import dev.darshan.schoolsys.dto.SubjectCountResponse;
import dev.darshan.schoolsys.service.ProfessorService;
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
@RequestMapping("/api/v1/professors")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessorController {

    ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorDto> createNewProfessor(@RequestBody ProfessorDto professorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                professorService.createNewProfessor(professorDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> getProfessor(@PathVariable Long id){
        return ResponseEntity.ok(
                professorService.getProfessorById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDto> updateProfessor(
            @PathVariable Long id,
            @RequestBody ProfessorDto professorDto) {
        return ResponseEntity.ok(professorService.updateProfessor(id, professorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id){
       return ResponseEntity.ok(professorService.deleteProfessorById(id));
    }

    @PutMapping("/{id}/subjects/{subjectId}")
    public ResponseEntity<Void> assignSubjectToProfessor(
            @PathVariable Long id,
            @PathVariable Long subjectId
    ){
        return ResponseEntity.ok(professorService.assignSubject(id, subjectId));
    }

    @PutMapping("/{profId}/subjects/{studentId}")
    public ResponseEntity<Void> assignStudentToProfessor(
            @PathVariable Long profId,
            @PathVariable Long studentId
    ){
        return ResponseEntity.ok(professorService.assignStudent(profId, studentId));
    }

    @GetMapping("/{profId}/students")
    public ResponseEntity<List<StudentDto>> getAllStudentsOfProf(@PathVariable Long profId){
        return ResponseEntity.ok(professorService.getAllStudentsOfProf(profId));
    }

    @GetMapping
    public ResponseEntity<Page<ProfessorDto>> getAllProfessors(
            @RequestParam(required = false) String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        if (department != null && !department.isEmpty()) {
            return ResponseEntity.ok(professorService.getProfessorsByDepartment(department, page, size, sortBy, direction));
        }

        return ResponseEntity.ok(professorService.getAllProfessors(page, size, sortBy, direction));
    }

    @GetMapping("/{professorId}/subjects/count")
    public ResponseEntity<SubjectCountResponse> getSubjectCountForProfessor(
            @PathVariable Long professorId
    ){
        return ResponseEntity.ok(professorService.getSubjectCountForProfessor(professorId));
    }

    @GetMapping("/department-summary")
    public ResponseEntity<List<DepartmentSummaryResponse>> getDepartmentSummary() {
        return ResponseEntity.ok(professorService.getDepartmentSummary());
    }
}
