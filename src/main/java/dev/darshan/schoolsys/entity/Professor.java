package dev.darshan.schoolsys.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
}