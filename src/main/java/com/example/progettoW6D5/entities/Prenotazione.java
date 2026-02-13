package com.example.progettoW6D5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni", uniqueConstraints = @UniqueConstraint(columnNames = {"dipendente_id", "data"})
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;


    @ManyToOne
    private Dipendente dipendente;


    @ManyToOne
    private Viaggio viaggio;


    private LocalDate data;

    private LocalDateTime dataRichiesta;

    private String note;

    public Prenotazione(Dipendente dipendente, Viaggio viaggio, LocalDate data, String note) {
        this.dipendente = dipendente;
        this.viaggio = viaggio;
        this.data = data;
        this.note = note;
        this.dataRichiesta = LocalDateTime.now();
    }
}
