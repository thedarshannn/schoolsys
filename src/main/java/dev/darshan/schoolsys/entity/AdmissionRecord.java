package dev.darshan.schoolsys.entity;

import dev.darshan.schoolsys.enums.FeesStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

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
}