package com.example.progettoW6D5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.progettoW6D5.entities.Prenotazione;
import com.example.progettoW6D5.exceptions.ValidationException;
import com.example.progettoW6D5.payloads.PrenotazioneDTO;
import com.example.progettoW6D5.services.PrenotazioniService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione crea(@RequestBody @Validated PrenotazioneDTO payload,
                             BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(
                    validationResult.getFieldErrors()
                            .stream()
                            .map(e -> e.getDefaultMessage())
                            .toList()
            );
        }
        return prenotazioniService.creaPrenotazione(payload);
    }
}
