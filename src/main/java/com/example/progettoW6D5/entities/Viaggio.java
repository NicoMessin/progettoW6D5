package com.example.progettoW6D5.entities;

import jakarta.persistence.*;
import lombok.*;
import  com.example.progettoW6D5.entities.StatoViaggio;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Viaggio {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String destinazione;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;

    public Viaggio(String destinazione, LocalDate data) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = StatoViaggio.IN_PROGRAMMA;
    }
}
