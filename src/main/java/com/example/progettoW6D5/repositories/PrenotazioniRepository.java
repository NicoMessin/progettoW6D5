package com.example.progettoW6D5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.progettoW6D5.entities.Dipendente;
import com.example.progettoW6D5.entities.Prenotazione;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByDipendenteAndData(Dipendente dipendente, LocalDate data);
}