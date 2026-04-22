package dev.darshan.schoolsys.entity;

import dev.darshan.schoolsys.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "student")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    String name;

    @Column(unique = true, nullable = false)
    String email;

    LocalDate enrollmentDate;

    Double gpa;

    @Enumerated(EnumType.STRING)
    StudentStatus status;

    @ManyToMany(mappedBy = "students")
    List<Professor> professors;

    @ManyToMany
    @JoinTable(name = "student_subject")
    List<Subject> subjects;

    @OneToOne(mappedBy = "student") // Inverse Side
    AdmissionRecord admissionRecord;

}