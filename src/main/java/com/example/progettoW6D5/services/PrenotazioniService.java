package com.example.progettoW6D5.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.progettoW6D5.entities.Dipendente;
import com.example.progettoW6D5.entities.Prenotazione;
import com.example.progettoW6D5.entities.Viaggio;
import com.example.progettoW6D5.exceptions.BadRequestException;
import com.example.progettoW6D5.payloads.PrenotazioneDTO;
import com.example.progettoW6D5.repositories.PrenotazioniRepository;

@Service
@Slf4j
public class PrenotazioniService {

    private final PrenotazioniRepository prenotazioniRepository;
    private final DipendentiService dipendentiService;
    private final ViaggiService viaggiService;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository,
                               DipendentiService dipendentiService,
                               ViaggiService viaggiService) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.dipendentiService = dipendentiService;
        this.viaggiService = viaggiService;
    }

    public Prenotazione creaPrenotazione(PrenotazioneDTO payload) {
        Dipendente dipendente = dipendentiService.findById(payload.dipendenteId());
        Viaggio viaggio = viaggiService.findById(payload.viaggioId());

        if (prenotazioniRepository.existsByDipendenteAndData(dipendente, payload.data())) {
            throw new BadRequestException("Dipendente già impegnato nella data " + payload.data());
        }

        Prenotazione nuova = new Prenotazione(dipendente, viaggio, payload.data(), payload.note());
        Prenotazione salvata = prenotazioniRepository.save(nuova);
        log.info("Prenotazione con id " + salvata.getId() + " creata correttamente");
        return salvata;
    }
}