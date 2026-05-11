package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.dto.TranscriptResponse;
import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStatus(StudentStatus status);

    List<Student> findByGpaGreaterThanEqual(Double gpaIsGreaterThan);

    @Query("SELECT s FROM Student s ORDER BY s.gpa DESC LIMIT :limit")
    List<Student> findTopStudentsByGpa(@Param("limit") int limit);


    @Query("""
    SELECT DISTINCT s FROM Student s
    LEFT JOIN FETCH s.professors
    LEFT JOIN FETCH s.subjects
    LEFT JOIN FETCH s.admissionRecord
    WHERE s.id = :id""")
    Optional<Student> findStudentWithFullProfile(@Param("id") Long id);
}