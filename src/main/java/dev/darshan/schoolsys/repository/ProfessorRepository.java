package dev.darshan.schoolsys.repository;

import dev.darshan.schoolsys.dto.DepartmentSummaryResponse;
import dev.darshan.schoolsys.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor getProfessorById(Long id);

    List<Professor> findByDepartment(String department);

    @Query("""
    SELECT new dev.darshan.schoolsys.dto.DepartmentSummaryResponse(
        p.department,
        COUNT(DISTINCT p.id),
        COUNT(DISTINCT st.id),
        COUNT(DISTINCT s.id)
    )
    FROM Professor p
    LEFT JOIN p.students st
    LEFT JOIN p.subjects s
    GROUP BY p.department""")
    List<DepartmentSummaryResponse> findDepartmentSummary();
}