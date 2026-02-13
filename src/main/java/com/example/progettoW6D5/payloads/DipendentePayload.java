package com.example.progettoW6D5.payloads;

public class DipendentePayload {
    private String username;
    private String nome;
    private String cognome;
    private String email;

    public DipendentePayload(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getEmail() { return email; }
}
