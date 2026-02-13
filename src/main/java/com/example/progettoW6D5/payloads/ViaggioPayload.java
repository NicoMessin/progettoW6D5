package com.example.progettoW6D5.payloads;

import java.time.LocalDate;

public class ViaggioPayload {
    private String destinazione;
    private LocalDate data;

    public ViaggioPayload(String destinazione, LocalDate data) {
        this.destinazione = destinazione;
        this.data = data;
    }

    public String getDestinazione() { return destinazione; }
    public LocalDate getData() { return data; }
}
