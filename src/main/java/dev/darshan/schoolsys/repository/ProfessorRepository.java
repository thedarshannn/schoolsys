package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor getProfessorById(Long id);
}