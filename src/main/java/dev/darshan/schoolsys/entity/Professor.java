package dev.darshan.schoolsys.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "professor")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    String firstName;

    String lastName;

    String title;

    @Column(unique = true)
    String email;

    String department;

    Double salary;

    LocalDate hireDate;

    @OneToMany(mappedBy = "professor", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Subject> subjects;

    @ManyToMany
    @JoinTable(name = "professor_student")
    List<Student> students;
}