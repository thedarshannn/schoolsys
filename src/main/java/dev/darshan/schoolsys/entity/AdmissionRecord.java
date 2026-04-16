package dev.darshan.schoolsys.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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

    BigDecimal fees;
}