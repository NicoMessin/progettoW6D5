package com.example.progettoW6D5.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "dipendenteId obbligatorio")
        UUID dipendenteId,

        @NotNull(message = "viaggioId obbligatorio")
        UUID viaggioId,

        @NotNull(message = "data obbligatoria")
        LocalDate data,

        String note
) {
}
