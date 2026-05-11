package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStatus(StudentStatus status);

    List<Student> findByGpaGreaterThanEqual(Double gpaIsGreaterThan);

    @Query("SELECT s FROM Student s ORDER BY s.gpa DESC LIMIT :limit")
    List<Student> findTopStudentsByGpa(@Param("limit") int limit);
}