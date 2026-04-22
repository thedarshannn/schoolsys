package dev.darshan.schoolsys.entity;

import dev.darshan.schoolsys.enums.FeesStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admission_record")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    Double fees;

    @Enumerated
    FeesStatus feesStatus;

    LocalDate admissionDate;

    String grade;

    @OneToOne
    @JoinColumn(name = "student_id")
    Student student;
}