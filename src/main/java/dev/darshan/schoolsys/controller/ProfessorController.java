package dev.darshan.schoolsys.controller;

import dev.darshan.schoolsys.dto.ProfessorDto;
import dev.darshan.schoolsys.service.ProfessorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Professor")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfessorController {

    ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorDto> createNewProfessor(@RequestBody ProfessorDto professorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                professorService.createNewProfessor(professorDto)
        );
    }
}
