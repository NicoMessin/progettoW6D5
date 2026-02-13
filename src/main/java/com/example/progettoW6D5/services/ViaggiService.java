package com.example.progettoW6D5.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.progettoW6D5.entities.Viaggio;
import com.example.progettoW6D5.entities.StatoViaggio;
import com.example.progettoW6D5.exceptions.NotFoundException;
import com.example.progettoW6D5.payloads.ViaggioDTO;
import com.example.progettoW6D5.payloads.ViaggioPayload;
import com.example.progettoW6D5.repositories.ViaggiRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ViaggiService {

    private final ViaggiRepository viaggiRepository;

    public ViaggiService(ViaggiRepository viaggiRepository) {
        this.viaggiRepository = viaggiRepository;
    }

    public Viaggio save(ViaggioDTO payload) {
        Viaggio nuovo = new Viaggio(payload.destinazione(), payload.data());
        Viaggio salvato = viaggiRepository.save(nuovo);
        log.info("Viaggio con id " + salvato.getId() + " salvato correttamente");
        return salvato;
    }

    public List<Viaggio> findAll() {
        return viaggiRepository.findAll();
    }

    public Viaggio findById(UUID id) {
        return viaggiRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio findByIdAndUpdate(UUID id, ViaggioPayload payload) {
        Viaggio found = findById(id);
        found.setDestinazione(payload.getDestinazione());
        found.setData(payload.getData());
        Viaggio modificato = viaggiRepository.save(found);
        log.info("Viaggio con id " + modificato.getId() + " modificato correttamente");
        return modificato;
    }

    public void findByIdAndDelete(UUID id) {
        Viaggio found = findById(id);
        viaggiRepository.delete(found);
    }

    public Viaggio cambiaStato(UUID id, StatoViaggio stato) {
        Viaggio found = findById(id);
        found.setStato(stato);
        return viaggiRepository.save(found);
    }
}