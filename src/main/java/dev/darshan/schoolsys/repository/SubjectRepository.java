package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}