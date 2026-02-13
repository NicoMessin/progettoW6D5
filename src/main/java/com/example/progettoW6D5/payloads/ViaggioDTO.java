package com.example.progettoW6D5.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "La destinazione è obbligatoria")
        @Size(min = 2, max = 50, message = "La destinazione deve essere tra 2 e 50 caratteri")
        String destinazione,

        @NotNull(message = "La data è obbligatoria")
        @FutureOrPresent(message = "La data non può essere nel passato")
        LocalDate data
) {
}
