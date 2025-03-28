package br.ueg.progwebi.collegeapi.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private String registerNumber;
    private String course;
    private LocalDate registerDate;
}
