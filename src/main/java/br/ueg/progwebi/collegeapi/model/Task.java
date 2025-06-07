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
public class Task {
    public static final String SEQUENCE_NAME="TASK_ID_GENERATE" ;
    @Id
    @Column(name = "id")
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
    @Column(name = "descricao", length = 200, nullable = false)
    private String description;
    @Column(name = "concluida", nullable = false)
    private Boolean completed;
}
