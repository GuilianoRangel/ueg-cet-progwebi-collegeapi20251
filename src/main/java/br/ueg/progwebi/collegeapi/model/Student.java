package br.ueg.progwebi.collegeapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {
    public static final String SEQUENCE_NAME="STUDENT_ID_GENERATE" ;
    @Id
    @Column(name = "codigo")
    @SequenceGenerator(
            name=SEQUENCE_NAME,
            sequenceName = SEQUENCE_NAME+"_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = SEQUENCE_NAME
    )
    private Long id;
    @Column(name = "NOME", length = 200, nullable = false, unique = true)
    private String name;
    private String registerNumber;
    @Column(name="CURSO", length = 150, nullable = false)
    private String course;
    private LocalDate registerDate;
}
