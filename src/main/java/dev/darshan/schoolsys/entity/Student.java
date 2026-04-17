package dev.darshan.schoolsys.entity;

import dev.darshan.schoolsys.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student")
@FieldDefaults(level = AccessLevel.PRIVATE)
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

    @Enumerated
    StudentStatus status;


}