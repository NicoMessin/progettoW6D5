package com.example.progettoW6D5.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.progettoW6D5.entities.Dipendente;
import com.example.progettoW6D5.exceptions.ValidationException;
import com.example.progettoW6D5.payloads.DipendenteDTO;
import com.example.progettoW6D5.payloads.DipendentePayload;
import com.example.progettoW6D5.services.DipendentiService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {

    private final DipendentiService dipendentiService;

    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente create(@RequestBody @Validated DipendenteDTO payload,
                             BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(
                    validationResult.getFieldErrors()
                            .stream()
                            .map(e -> e.getDefaultMessage())
                            .toList()
            );
        }
        return dipendentiService.save(payload);
    }

    @GetMapping
    public List<Dipendente> findAll() {
        return dipendentiService.findAll();
    }

    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable UUID id) {
        return dipendentiService.findById(id);
    }

    @PutMapping("/{id}")
    public Dipendente update(@PathVariable UUID id,
                             @RequestBody DipendentePayload payload) {
        return dipendentiService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        dipendentiService.findByIdAndDelete(id);
    }}
