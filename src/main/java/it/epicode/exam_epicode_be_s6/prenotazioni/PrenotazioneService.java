package it.epicode.exam_epicode_be_s6.prenotazioni;

import it.epicode.exam_epicode_be_s6.dipendenti.Dipendente;
import it.epicode.exam_epicode_be_s6.dipendenti.DipendenteRepository;
import it.epicode.exam_epicode_be_s6.viaggi.Viaggio;
import it.epicode.exam_epicode_be_s6.viaggi.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired private DipendenteRepository dipendenteRepository;
    @Autowired private ViaggioRepository viaggioRepository;
    @Autowired private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione save(Prenotazione prenotazione) {
        if (prenotazione.getDipendente() == null || prenotazione.getDipendente().getId() == null) {
            throw new IllegalArgumentException("ID del dipendente mancante");
        }

        if (prenotazione.getViaggio() == null || prenotazione.getViaggio().getId() == null) {
            throw new IllegalArgumentException("ID del viaggio mancante");
        }

        Dipendente dipendente = dipendenteRepository.findById(prenotazione.getDipendente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(prenotazione.getViaggio().getId())
                .orElseThrow(() -> new IllegalArgumentException("Viaggio non trovato"));

        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        return prenotazioneRepository.save(prenotazione);
    }
}
