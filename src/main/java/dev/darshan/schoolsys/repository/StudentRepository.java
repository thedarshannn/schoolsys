package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}