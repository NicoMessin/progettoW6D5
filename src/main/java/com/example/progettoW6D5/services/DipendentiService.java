package com.example.progettoW6D5.services;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.progettoW6D5.entities.Dipendente;
import com.example.progettoW6D5.exceptions.BadRequestException;
import com.example.progettoW6D5.exceptions.NotFoundException;
import com.example.progettoW6D5.payloads.DipendenteDTO;
import com.example.progettoW6D5.payloads.DipendentePayload;
import com.example.progettoW6D5.repositories.DipendentiRepository;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DipendentiService {

    private final DipendentiRepository dipendentiRepository;
    private final Cloudinary cloudinaryUploader;

    public DipendentiService(DipendentiRepository dipendentiRepository, Cloudinary cloudinaryUploader) {
        this.dipendentiRepository = dipendentiRepository;
        this.cloudinaryUploader = cloudinaryUploader;
    }

    public Dipendente save(DipendenteDTO payload) {
        dipendentiRepository.findByEmail(payload.email()).ifPresent(d -> {
            throw new BadRequestException("L'email " + d.getEmail() + " è già in uso!");
        });

        Dipendente nuovo = new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email());
        nuovo.setAvatarURL("https://ui-avatars.com/api?name=" + payload.nome() + "+" + payload.cognome());

        Dipendente salvato = dipendentiRepository.save(nuovo);
        log.info("Dipendente con id " + salvato.getId() + " salvato correttamente");
        return salvato;
    }

    public List<Dipendente> findAll() {
        return dipendentiRepository.findAll();
    }

    public Dipendente findById(UUID id) {
        return dipendentiRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendente findByIdAndUpdate(UUID id, DipendentePayload payload) {
        Dipendente found = findById(id);

        if (!found.getEmail().equals(payload.getEmail())) {
            dipendentiRepository.findByEmail(payload.getEmail()).ifPresent(d -> {
                throw new BadRequestException("L'email " + d.getEmail() + " è già in uso!");
            });
        }

        found.setUsername(payload.getUsername());
        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setEmail(payload.getEmail());
        found.setAvatarURL("https://ui-avatars.com/api?name=" + payload.getNome() + "+" + payload.getCognome());

        Dipendente modificato = dipendentiRepository.save(found);
        log.info("Dipendente con id " + modificato.getId() + " modificato correttamente");
        return modificato;
    }

    public void findByIdAndDelete(UUID id) {
        Dipendente found = findById(id);
        dipendentiRepository.delete(found);
    }


}
