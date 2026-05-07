package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.entity.Student;
import dev.darshan.schoolsys.enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStatus(StudentStatus status);

    List<Student> findByGpaGreaterThanEqual(Double gpaIsGreaterThan);

}