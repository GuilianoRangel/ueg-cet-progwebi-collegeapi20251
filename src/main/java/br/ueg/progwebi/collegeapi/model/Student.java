package br.ueg.progwebi.collegeapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {
    @Id
    @Column(name = "codigo")
    private Long id;
    @Column(name = "NOME", length = 200, nullable = false, unique = true)
    private String name;
    private String registerNumber;
    @Column(name="CURSO", length = 150, nullable = false)
    private String course;
    private LocalDate registerDate;
}
