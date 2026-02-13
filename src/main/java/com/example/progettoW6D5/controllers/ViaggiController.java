package com.example.progettoW6D5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.progettoW6D5.entities.Viaggio;
import com.example.progettoW6D5.entities.StatoViaggio;
import com.example.progettoW6D5.exceptions.ValidationException;
import com.example.progettoW6D5.payloads.ViaggioDTO;
import com.example.progettoW6D5.payloads.ViaggioPayload;
import com.example.progettoW6D5.services.ViaggiService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {

    private final ViaggiService viaggiService;

    public ViaggiController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio create(@RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(
                    validationResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).toList()
            );
        }
        return viaggiService.save(payload);
    }

    @GetMapping
    public List<Viaggio> findAll() {
        return viaggiService.findAll();
    }

    @GetMapping("/{id}")
    public Viaggio findById(@PathVariable UUID id) {
        return viaggiService.findById(id);
    }

    @PutMapping("/{id}")
    public Viaggio update(@PathVariable UUID id,
                          @RequestBody ViaggioPayload payload) {
        return viaggiService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        viaggiService.findByIdAndDelete(id);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio cambiaStato(@PathVariable UUID id,
                               @RequestParam StatoViaggio stato) {
        return viaggiService.cambiaStato(id, stato);
    }

}

