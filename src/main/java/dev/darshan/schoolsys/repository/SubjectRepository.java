package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> getSubjectsBySemester(String semester);

    long countSubjectByProfessor_Id(Long professorId);


    @Query("SELECT s FROM Subject s WHERE SIZE(s.students) < s.maxCapacity ORDER BY s.id")
    List<Subject> findAvailableSubjects();
}