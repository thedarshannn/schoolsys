package dev.darshan.schoolsys.entity;

import dev.darshan.schoolsys.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    Set<Professor> professors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "student_subject")
    Set<Subject> subjects = new HashSet<>();

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true) // Inverse Side
    AdmissionRecord admissionRecord;

}