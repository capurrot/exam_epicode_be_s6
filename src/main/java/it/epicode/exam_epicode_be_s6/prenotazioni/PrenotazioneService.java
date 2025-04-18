package it.epicode.exam_epicode_be_s6.prenotazioni;

import it.epicode.exam_epicode_be_s6.dipendenti.Dipendente;
import it.epicode.exam_epicode_be_s6.dipendenti.DipendenteRepository;
import it.epicode.exam_epicode_be_s6.exceptions.PrenotazioneConflittoException;
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
        Long dipendenteId = prenotazione.getDipendente().getId();
        Long viaggioId = prenotazione.getViaggio().getId();

        if (dipendenteId == null) throw new IllegalArgumentException("ID del dipendente mancante");
        if (viaggioId == null) throw new IllegalArgumentException("ID del viaggio mancante");

        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new IllegalArgumentException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new IllegalArgumentException("Viaggio non trovato"));

        boolean haConflitto = prenotazioneRepository.existsByDipendenteAndDateOverlap(
                dipendenteId,
                viaggio.getDataPartenza(),
                viaggio.getDataRitorno()
        );
        if (haConflitto) {
            throw new PrenotazioneConflittoException("Il dipendente ha già una prenotazione in quelle date.");
        }
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id).orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
    }

    public Iterable<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    public void delete(Long id) {
        prenotazioneRepository.deleteById(id);
    }
}
